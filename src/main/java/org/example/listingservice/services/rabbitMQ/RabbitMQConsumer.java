package org.example.listingservice.services.rabbitMQ;

import jakarta.transaction.Transactional;
import org.example.listingservice.configurations.RabbitMQConfiguration;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.RegisterDTO;
import org.example.listingservice.messages.AvatarMessage;
import org.example.listingservice.messages.PaymentMessage;
import org.example.listingservice.repositories.BuildingImageRepository;
import org.example.listingservice.repositories.BuildingRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.services.DriveService;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.BuildingImage;
import org.example.listingservice.models.Payment;
import org.example.listingservice.models.User;
import org.example.listingservice.repositories.PaymentRepository;
import org.example.listingservice.services.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumer {
    private final MailService mailService;
    private final DriveService driveService;
    private final BuildingRepository buildingRepository;
    private final BuildingImageRepository buildingImageRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;


    @RabbitListener(queues = RabbitMQConfiguration.EMAIL_QUEUE)
    @Transactional
    public void handleSendMail(PaymentMessage message) throws Exception {
        Payment existingPayment = paymentRepository.findById(message.getId()).orElseThrow(()-> new DataNotFoundException(""));
        mailService.sendHtmlEmail(message.getEmail(), MessageKeys.PAYMENT_SUCCESS+ message.getId(),existingPayment);
    }

    @RabbitListener(queues = RabbitMQConfiguration.UPLOADS_QUEUE)
    @Transactional
    public void handleUploadImage(AvatarMessage message) throws GeneralSecurityException, IOException, DataNotFoundException {
        Building existingBuilding = buildingRepository.findById(message.getBuildingId())
                .orElseThrow(() -> new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));

        if(message.getFiles() == null || message.getFiles().isEmpty()){
            try {
                String fileId = driveService.extractFileIdFromUrl(existingBuilding.getAvatar());
                if (fileId != null) {
                    driveService.deleteImageFromDrive(fileId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            File avatarTempFile = File.createTempFile("avatar", null);
            try (FileOutputStream fos = new FileOutputStream(avatarTempFile)) {
                fos.write(message.getFile());  // Ghi byte[] vào file tạm
            }

            String avatarUrl = driveService.uploadImageToDrive(avatarTempFile);
            existingBuilding.setAvatar(avatarUrl);


        }
        else{
            List<BuildingImage> existingImages = existingBuilding.getImages();
            existingImages.forEach(image -> {
                buildingImageRepository.delete(image);
                try {
                    String fileId = driveService.extractFileIdFromUrl(image.getImageUrl());
                    if (fileId != null) {
                        driveService.deleteImageFromDrive(fileId);
                    } else {
                        System.out.println("Invalid fileId found in imageUrl: " + image.getImageUrl());
                    }
                } catch (GeneralSecurityException | IOException e) {
                    e.printStackTrace();
                }
            });

            // Xóa tất cả các hình ảnh hiện có
            existingImages.clear();

            List<String> imageUrls = new ArrayList<>();
            for (byte[] file : message.getFiles()) {
                File imageTempFile = File.createTempFile("image", null);
                try (FileOutputStream fos = new FileOutputStream(imageTempFile)) {
                    fos.write(file);
                }
                String imageUrl = driveService.uploadImageToDrive(imageTempFile);
                imageUrls.add(imageUrl);
            }

            List<BuildingImage> buildingImages = new ArrayList<>();
            for (String imageUrl : imageUrls) {
                BuildingImage build = BuildingImage.builder()
                        .building(existingBuilding)
                        .imageUrl(imageUrl)
                        .description("slider image for " + existingBuilding.getName())
                        .build();
                BuildingImage saved = buildingImageRepository.saveAndFlush(build);
                buildingImages.add(saved);
            }
            existingBuilding.setImages(buildingImages);
        }
        buildingRepository.save(existingBuilding);
    }


    @RabbitListener(queues = RabbitMQConfiguration.EMAIL_REGISTER_QUEUE)
    @Transactional
    public void sendMail(RegisterDTO dto) throws Exception {
        User newUser = userRepository.findByPhone(dto.getPhone())
                .orElseThrow(()-> new DataNotFoundException("Can not find user with phone = "+dto.getPhone()));

        mailService.sendHtmlEmail(dto.getEmail(), MessageKeys.REGISTER_SUCCESSFULLY, newUser);

    }




}
