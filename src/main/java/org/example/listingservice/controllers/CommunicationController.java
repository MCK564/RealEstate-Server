package org.example.listingservice.controllers;

import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.CommunicationDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
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
            return ResponseEntity.ok().body(communicationService.getAllByKeyWord(keyword,page,limit));
    }

    @PostMapping()
    public ResponseEntity<?> createOrUpdate(@RequestBody CommunicationDTO dto) throws DataNotFoundException {
            return ResponseEntity.ok().body(communicationService.createOrUpdate(dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAllByUserId (@PathVariable("id")Long id,
                                            @RequestParam(value = "buildingName",required = false)String buildingName,
                                             @RequestParam  int page,
                                             @RequestParam int limit){
            return ResponseEntity.ok().body(communicationService.getAllBySaler_IDOrUserId(id,buildingName,page,limit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long id) throws DataNotFoundException {
            communicationService.deleteCommunication(id);
            return ResponseEntity.ok().body(MessageKeys.DELETE_SUCCESSFULLY);
    }

    @GetMapping("/building/{id}")
    public ResponseEntity<?> getAllByBuildingId(@PathVariable("id")Long id) throws DataNotFoundException {
            return ResponseEntity.ok().body(communicationService.getAllByBuildingId(id));
    }
}
