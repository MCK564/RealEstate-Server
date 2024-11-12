package org.example.listingservice.messages;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PaymentMessage implements Serializable {
    private static Long UUIDversionID= 1L;
    private Long id;
    private String email;
}
