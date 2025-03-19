package org.example.listingservice.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class DriveService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoogleCredentials();
    private static final String folderId = "1nDrDNoWmBDfazuy35FcvEfBCQ2CHGrhe";

    @Value("${spring.application.name}")
    private String applicationName;

    private static String getPathToGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory,"./cred.json");
        return filePath.toString();
    }

    public String uploadImageToDrive(File file) throws GeneralSecurityException, IOException {
        try {
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg",file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String imageUrl = "https://drive.google.com/thumbnail?id="+uploadedFile.getId();
            System.out.println("IMAGE URL: "+imageUrl);
            file.delete();
            return imageUrl;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public String updateImageInDrive(String fileId, File newImageFile) throws GeneralSecurityException, IOException {

        try {
            Drive drive = createDriveService();
            FileContent mediaContent = new FileContent("image/jpeg", newImageFile);
            com.google.api.services.drive.model.File updatedFile = drive.files().update(fileId, null, mediaContent)
                    .setFields("id").execute();
            String imageUrl = "https://drive.google.com/thumbnail?id="+updatedFile.getId();
            System.out.println("UPDATED IMAGE URL: "+imageUrl);
            newImageFile.delete();
            return imageUrl;
        } catch(Exception e) {
            System.out.println(e.getMessage());
         return null;
        }
    }

    public void deleteImageFromDrive(String fileId) throws GeneralSecurityException, IOException {
        try {
            Drive drive = createDriveService();
            drive.files().delete(fileId).execute();
            System.out.println("File deleted successfully.");
        } catch(Exception e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }


    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singletonList(DriveScopes.DRIVE));

        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, credential)
                .setApplicationName(applicationName).build();
    }

    public String extractFileIdFromUrl(String imageUrl) {
        try {
            int idIndex = imageUrl.indexOf("id=");
            if (idIndex != -1) {
                return imageUrl.substring(idIndex + 3);
            }
        } catch (Exception e) {
            System.out.println("Error extracting fileId from URL: " + e.getMessage());
        }
        return null;
    }
}

