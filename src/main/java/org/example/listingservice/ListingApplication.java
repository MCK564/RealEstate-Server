package org.example.listingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = {"org.example.listingservice.models"})
@EnableCaching
public class ListingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListingApplication.class, args);
    }
}
