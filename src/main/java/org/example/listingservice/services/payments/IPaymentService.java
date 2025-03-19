package org.example.listingservice.services.payments;

import org.example.listingservice.dtos.PaymentDTO;
import org.example.listingservice.responses.payment.PaymentListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Map;

@Service
public interface IPaymentService {
    String getVNPayURL(PaymentDTO dto);
    RedirectView handleVNPayReturnURL(Map<String, String> params) throws Exception;
    PaymentListResponse adminGetPayments(int year, int month, int page, int limit);
    PaymentListResponse getAllByUserId(Long userId);
    Double getTotalRevenue(int year, int month);
    String downloadExcelUrl(int year, int month)throws IOException;

}
