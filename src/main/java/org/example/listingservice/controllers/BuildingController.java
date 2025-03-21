package org.example.listingservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.BuildingDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
import org.example.listingservice.models.BuildingImage;
import org.example.listingservice.services.DriveService;
import org.example.listingservice.services.GoogleVisionService;
import org.example.listingservice.services.buildingImage.BuildingImageService;
import org.example.listingservice.services.buildings.BuildingService;
import org.example.listingservice.utils.LocalizationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("${api.prefix}/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final DriveService driveService;
    private final BuildingService buildingService;
    private final LocalizationUtils localizationUtils;
    private final BuildingImageService buildingImageService;
    private final GoogleVisionService googleVisionService;

    @GetMapping("/search")
    public ResponseEntity<?> getAllBuildingByKeyword(
            @RequestParam Map<String,Object> params,
            @RequestParam(value="type",required = false) List<String> type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit){
            return ResponseEntity.ok(buildingService.findByCondition(params,page,limit,type));
    }

    @GetMapping("/relations/{id}")
    public ResponseEntity<?> get50SimilarBuildings(@PathVariable("id")Long id) throws DataNotFoundException {
            return ResponseEntity.ok().body(buildingService.getRelativeBuildingsByBuildingId(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createOrUpdate(@RequestBody BuildingDTO buildingDTO) throws DataNotFoundException {
         return buildingService.createOrUpdate(buildingDTO);
    }


    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getAllByOwnerId(@PathVariable("id")Long id){
            return ResponseEntity.ok().body(buildingService.findByOwnerId(id));
    }


    @GetMapping("/popular")
    public ResponseEntity<?> getSomePopularBuildings(){
            return ResponseEntity.ok().body(buildingService.getSomePopularBuilding());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBuildingById(@PathVariable("id")Long id) throws DataNotFoundException {
        return ResponseEntity.ok().body(buildingService.getById(id));
    }

    @PreAuthorize("@buildingService.isOwner(#buildingId, authentication.principal.username)")
    @PostMapping(value="uploads/{id}",
            consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages
            (@PathVariable("id")Long buildingId,
             @RequestParam("images")List<MultipartFile> files) throws IOException, DataNotFoundException, GeneralSecurityException {

        if(files.size()> BuildingImage.MAXIMUM_IMAGES_PER_BUILDING){
            return ResponseEntity.badRequest().body(
                    localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_MAX_5));
        }
        for(MultipartFile file: files){
            if(file.getSize() ==0){
                continue;
            }
            if(file.getSize()>10*1024*1024){ //10MB
             return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
                     localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_FILE_LARGE));
            }
            String contentType = file.getContentType();
            if(contentType == null || !contentType.startsWith("image/")){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                        localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_FILE_MUST_BE_IMAGE)
                );
            }
        }

            if(!googleVisionService.checkSensitive(files)){
                return ResponseEntity.status(400).body(MessageKeys.UPLOAD_UNSUCCESSFULLY);
            }
        return ResponseEntity.ok().body(buildingImageService.uploadImages(files,buildingId));
    }

    @PreAuthorize("@buildingService.isOwner(#buildingId, authentication.principal.username)")
    @PostMapping(value="/avatar/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadsAvatar(
            @PathVariable("id")Long buildingId,
            @RequestParam("avatar")MultipartFile file
            ){
        try{
            if(file.getSize()>10*1024*1024){ //10MB
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
                        localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_FILE_LARGE));
            }
            String contentType = file.getContentType();
            if(contentType == null || !contentType.startsWith("image/")){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                        localizationUtils.getLocalizedMesage(MessageKeys.UPLOAD_IMAGES_FILE_MUST_BE_IMAGE)
                );
            }
            if(!googleVisionService.checkSensitive(file)){
                return ResponseEntity.badRequest()
                        .body(MessageKeys.UPLOAD_UNSUCCESSFULLY + "because the image contains sensitive content");
            }
            return ResponseEntity.ok().body(buildingImageService.uploadAvatar(file,buildingId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("@buildingService.isOwner(#id, authentication.principal.username)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuildingById(@PathVariable("id")Long id) throws DataNotFoundException {
            buildingService.deleteById(id);
            return ResponseEntity.ok().body(MessageKeys.DELETE_SUCCESSFULLY);
    }

    @PreAuthorize("@buildingService.isOwner(#id, authentication.principal.username)")
    @GetMapping("/building-edit/{id}")
    public ResponseEntity<?> getBuildingEditById(@PathVariable("id")Long id) throws DataNotFoundException {
            return ResponseEntity.ok().body(buildingService.getBuildingEditById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/search")
    public ResponseEntity<?> getBuildingEditList(
            @RequestParam Map<String,Object> params,
            @RequestParam(value="type",required = false) List<String> type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit){
            return ResponseEntity.ok().body(buildingService.getBuildingEdits(params,page,limit,type));
    }

}
