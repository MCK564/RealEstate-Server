package org.example.listingservice.models;

import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Table(name = "communication")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buyer_renter_id")
    private User buyerRenter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sale_id")
    private User saler;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="building_id")
    private Building building;
}
