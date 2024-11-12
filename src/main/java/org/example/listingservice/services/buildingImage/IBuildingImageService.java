package org.example.listingservice.services.buildingImage;

import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.responses.building.BuildingResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IBuildingImageService {
    BuildingResponse uploadImages(List<MultipartFile> files,Long buildingId) throws IOException, GeneralSecurityException, DataNotFoundException;
    BuildingResponse uploadAvatar(MultipartFile files,Long buildingId) throws IOException, DataNotFoundException, GeneralSecurityException;
}
