package org.example.listingservice.responses.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.listingservice.models.Payment;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentListResponse {
    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_paid")
    private Double totalPaid;


    @JsonProperty("total_revenue")
    private Double totalRevenue;

    @JsonProperty("payment_responses")
    private List<PaymentDetailResponse> paymentResponses = new ArrayList<>();


    public static Double calculateTotalPaid(List<Payment> payments){
        return payments.stream()
                .mapToDouble(Payment::getMoney)
                .sum();
    }
}
