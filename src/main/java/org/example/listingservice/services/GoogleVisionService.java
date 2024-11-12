package org.example.listingservice.services;


import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.example.listingservice.dtos.ImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleVisionService {
    private final ImageAnnotatorClient client;

    private final List<String> sensitiveContentList = Arrays.asList(
            "Muscle", "Human body", "Gesture", "Swimwear", "Leg", "Finger", "Hair",
            "People on beach" , "People in nature", "Muscle", "Mouth" ,"Cheek", "Beauty"
            ,"Smile", "Beauty", "Close-up", "Thigh", "Eyelash", "Poster", "Games" , "Navel",
            "Advertising", "Fictional character"
    );


    private ImageResponseDTO detectContent(MultipartFile file) throws IOException {
        ByteString imgBytes = ByteString.readFrom(file.getInputStream());

        List<AnnotateImageRequest> requestList = List.of(
                AnnotateImageRequest.newBuilder()
                        .addFeatures(Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build())
                        .addFeatures(Feature.newBuilder().setType(Feature.Type.SAFE_SEARCH_DETECTION).build())
                        .setImage(Image.newBuilder().setContent(imgBytes).build())
                        .build()
        );

        List<AnnotateImageResponse> responses = client.batchAnnotateImages(requestList).getResponsesList();

        if (responses.isEmpty() || responses.get(0).hasError()) {
            throw new RuntimeException("Error in response: " + (responses.isEmpty() ? "No response." : responses.get(0).getError().getMessage()));
        }

        List<EntityAnnotation> labels = responses.get(0).getLabelAnnotationsList();
        List<EntityAnnotation> sensitiveLabels =  labels.stream().filter(l -> isSensitive(l.getDescription())).toList();
        if(sensitiveLabels.size()>3){
            return ImageResponseDTO.builder()
                    .content("sensitive content")
                    .isAccepted(false)
                    .build();
        }


        SafeSearchAnnotation safeSearch = responses.get(0).getSafeSearchAnnotation();
        if (safeSearch.getAdult() == Likelihood.LIKELY ||
                safeSearch.getViolence() == Likelihood.LIKELY ||
                safeSearch.getRacy() == Likelihood.LIKELY) {
            return ImageResponseDTO.builder()
                    .content("sensitive content")
                    .isAccepted(false)
                    .build();
        }

        return ImageResponseDTO.builder()
                .isAccepted(true)
                .content("standard content")
                .build();
    }

    private boolean isSensitive(String label){
            return sensitiveContentList.contains(label);
    }

    public boolean checkSensitive(List<MultipartFile> files) throws IOException {

        for(MultipartFile file: files){
         if(!detectContent(file).getIsAccepted()) return false;
        }
        return true;
    }

    public boolean checkSensitive(MultipartFile file) throws IOException{
        return detectContent(file).getIsAccepted();
    }
}
