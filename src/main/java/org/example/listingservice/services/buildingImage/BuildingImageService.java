package org.example.listingservice.services.buildingImage;

import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.messages.AvatarMessage;
import org.example.listingservice.models.Building;
import org.example.listingservice.models.BuildingImage;
import org.example.listingservice.repositories.BuildingImageRepository;
import org.example.listingservice.repositories.BuildingRepository;
import org.example.listingservice.responses.building.BuildingResponse;
import org.example.listingservice.services.DriveService;
import org.example.listingservice.services.rabbitMQ.RabbitMQProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BuildingImageService implements IBuildingImageService{
    private final BuildingRepository buildingRepository;
    private final BuildingImageRepository buildingImageRepository;
    private final DriveService driveService;
    private final RabbitMQProducer rabbitMQProducer;

    @Override
    @Transactional
    public BuildingResponse uploadImages(List<MultipartFile> files, Long buildingId) throws IOException, GeneralSecurityException, DataNotFoundException {
        Building existingBuilding = buildingRepository.findById(buildingId).orElseThrow(
                ()->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));

//        List<BuildingImage> existingImages = existingBuilding.getImages();
//        existingImages.forEach(image -> {
//            buildingImageRepository.delete(image);
//            try {
//                String fileId = driveService.extractFileIdFromUrl(image.getImageUrl());
//                if (fileId != null) {
//                    driveService.deleteImageFromDrive(fileId);
//                } else {
//                    System.out.println("Invalid fileId found in imageUrl: " + image.getImageUrl());
//                }
//            } catch (GeneralSecurityException | IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        // Xóa tất cả các hình ảnh hiện có
//        existingImages.clear();
//
//        List<String> imageUrls = new ArrayList<>();
//        for (MultipartFile file : files) {
//            File imageTempFile = File.createTempFile("image", null);
//            file.transferTo(imageTempFile);
//            String imageUrl = driveService.uploadImageToDrive(imageTempFile);
//            imageUrls.add(imageUrl);
//        }
//
//        List<BuildingImage> buildingImages = new ArrayList<>();
//        for (String imageUrl : imageUrls) {
//            BuildingImage build = BuildingImage.builder()
//                    .building(existingBuilding)
//                    .imageUrl(imageUrl)
//                    .description("slider image for " + existingBuilding.getName())
//                    .build();
//            BuildingImage saved = buildingImageRepository.saveAndFlush(build);
//            buildingImages.add(saved);
//        }
//        existingBuilding.setImages(buildingImages);
        List<byte[]> bytes = files.stream().map(file->{
            try{
                return file.getBytes();
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }).filter(Objects::nonNull)
                        .collect(Collectors.toList());
        rabbitMQProducer.uploadImage(AvatarMessage.builder()
                        .buildingId(buildingId)
                        .files(bytes)
                .build());
        return BuildingResponse.fromBuilding(buildingRepository.saveAndFlush(existingBuilding));
    }


    @Override
    public BuildingResponse uploadAvatar(MultipartFile file,Long buildingId) throws IOException, DataNotFoundException, GeneralSecurityException {
        Building existingBuilding = buildingRepository.findById(buildingId).orElseThrow(
                ()->new DataNotFoundException(MessageKeys.DATA_NOT_FOUND));
//        try {
//            String fileId = driveService.extractFileIdFromUrl(existingBuilding.getAvatar());
//            if (fileId != null) {
//                driveService.deleteImageFromDrive(fileId);
//            } else {
//                System.out.println("Invalid fileId found in imageUrl: " + existingBuilding.getAvatar());
//            }
//        } catch (GeneralSecurityException | IOException e) {
//            e.printStackTrace();
//        }
//        File avatarTempFile = File.createTempFile("avatar", null);
//        file.transferTo(avatarTempFile);
//        String avatarUrl = driveService.uploadImageToDrive(avatarTempFile);
//        existingBuilding.setAvatar(avatarUrl);
        byte[] imageContent = file.getBytes();
        rabbitMQProducer.uploadImage(AvatarMessage.builder()
                        .buildingId(buildingId)
                        .file(imageContent)
                .build());
        return BuildingResponse.fromBuilding(buildingRepository.saveAndFlush(existingBuilding));
}

private boolean deleteBuildingImage (BuildingImage image){
        try{
            buildingImageRepository.delete(image);
            return true;
        }catch(Exception e){
            return false;
        }
}

}
