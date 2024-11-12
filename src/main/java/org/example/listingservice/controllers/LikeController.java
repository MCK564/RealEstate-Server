package org.example.listingservice.controllers;

import org.example.listingservice.dtos.LikeDTO;
import org.example.listingservice.services.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/building/{id}")
    public ResponseEntity<?> getAllLikesByBuildingId(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(likeService.getAllByBuildingId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllLikesByUserId(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(likeService.getAllByUserId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> like(@RequestBody LikeDTO dto){
        try{
            return ResponseEntity.ok(likeService.like(dto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> dislike(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(likeService.dislike(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
