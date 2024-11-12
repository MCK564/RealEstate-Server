package org.example.listingservice.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

@Configuration
public class GoogleCloudConfig {
    @Bean
    public ImageAnnotatorClient imageAnnotatorClient() throws Exception {
        InputStream credStream = new FileInputStream("D:/TDTU documentaries/Java_Enterprise/RealEstate-Server/ocr_cred2.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(Objects.requireNonNull(credStream));
        ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();
        return ImageAnnotatorClient.create(settings);
    }
}
