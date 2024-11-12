package org.example.listingservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.listingservice.dtos.PaymentDTO;
import org.example.listingservice.services.MailService;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.services.payments.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final MailService mailService;

    @Value("${vnpay.return_client_url}")
    private String returnClientUrl;


    @GetMapping("/search")
    public ResponseEntity<?> searchPaymentByConditions(
          @RequestParam(defaultValue ="2024")int year,
            @RequestParam(defaultValue = "1")int month,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int limit){
    try{
        return ResponseEntity.ok().body(paymentService.adminGetPayments(year, month, page, limit));
    }catch(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }


    @GetMapping("/{user_id}")
    public ResponseEntity<?> getPaymentHistoryByUserID(
            @PathVariable("user_id")Long userId
    ){
        try{
            return ResponseEntity.ok().body(paymentService.getAllByUserId(userId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> buyPost(
            @RequestBody() PaymentDTO paymentDTO,
            HttpServletRequest request
            ){
        try{
            String payUrl = paymentService.createPaymentUrl(paymentDTO);
            return ResponseEntity.ok(payUrl);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/vnpay_return")
    public RedirectView handleVNPayReturn(@RequestParam Map<String,String> params){
        try{
            return paymentService.handleVNPayReturnURL(params);
        }catch(Exception e){
            return new RedirectView(returnClientUrl+"failed");
        }
    }



}
