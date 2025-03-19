package org.example.listingservice.controllers;

import org.example.listingservice.dtos.LikeDTO;
import org.example.listingservice.exceptions.DataNotFoundException;
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
            return ResponseEntity.ok().body(likeService.getAllByBuildingId(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllLikesByUserId(@PathVariable("id") Long id) {
            return ResponseEntity.ok().body(likeService.getAllByUserId(id));
    }

    @PostMapping
    public ResponseEntity<?> like(@RequestBody LikeDTO dto) throws DataNotFoundException {
            return ResponseEntity.ok(likeService.like(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> dislike(@PathVariable("id") Long id) throws DataNotFoundException {
            return ResponseEntity.ok(likeService.dislike(id));
    }
}
