package org.example.listingservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/test")
public class TestController {
    @GetMapping("")
    public ResponseEntity<?> greeting(){
        return ResponseEntity.ok("hahaha");
    }
}
