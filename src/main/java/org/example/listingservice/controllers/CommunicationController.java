package org.example.listingservice.controllers;

import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.CommunicationDTO;
import org.example.listingservice.services.communication.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/communications")
@RequiredArgsConstructor
public class CommunicationController {
    private final CommunicationService communicationService;

    @GetMapping("/search")
    public ResponseEntity<?> getAllByKeyWord (
            @RequestParam String keyword,
            @RequestParam  int page,
            @RequestParam int limit){
        try{
            return ResponseEntity.ok().body(communicationService.getAllByKeyWord(keyword,page,limit));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createOrUpdate(@RequestBody CommunicationDTO dto){
        try{
            return ResponseEntity.ok().body(communicationService.createOrUpdate(dto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAllByUserId (@PathVariable("id")Long id,
                                            @RequestParam(value = "buildingName",required = false)String buildingName,
                                             @RequestParam  int page,
                                             @RequestParam int limit){
        try{
            return ResponseEntity.ok().body(communicationService.getAllBySaler_IDOrUserId(id,buildingName,page,limit));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
        try{
            communicationService.deleteCommunication(id);
            return ResponseEntity.ok().body(MessageKeys.DELETE_SUCCESSFULLY);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/building/{id}")
    public ResponseEntity<?> getAllByBuildingId(@PathVariable("id")Long id
                                                ){
        try{
            return ResponseEntity.ok().body(communicationService.getAllByBuildingId(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }






}
