package org.example.listingservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.listingservice.dtos.PaymentDTO;
import org.example.listingservice.services.MailService;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.services.payments.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok().body(paymentService.adminGetPayments(year, month, page, limit));
    }

    @PreAuthorize("@paymentService.isOwner(#userId, authentication.principal.username)")
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getPaymentHistoryByUserID(
            @PathVariable("user_id")Long userId){
            return ResponseEntity.ok().body(paymentService.getAllByUserId(userId));
    }


    @PostMapping("")
    public ResponseEntity<?> buyPost(
            @RequestBody() PaymentDTO paymentDTO,
            HttpServletRequest request
            ) throws Exception {
            String payUrl = paymentService.createPaymentUrl(paymentDTO);
            return ResponseEntity.ok(payUrl);
    }

    @GetMapping("/vnpay_return")
    public RedirectView handleVNPayReturn(@RequestParam Map<String,String> params){
        try{
            return paymentService.handleVNPayReturnURL(params);
        }catch(Exception e){
            return new RedirectView(returnClientUrl+"failed");
        }
    }

    @GetMapping("/revenue_excel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> exportAndDownloadExcelFile(@RequestParam int year, @RequestParam int month){
        return ResponseEntity.ok("url");
    }

    @GetMapping("/total_revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTotalRevenue(@RequestParam int year, @RequestParam int month){
        return ResponseEntity.ok(paymentService.getTotalRevenue(year, month).intValue());
    }
}
