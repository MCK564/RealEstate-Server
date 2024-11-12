package org.example.listingservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;


@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "role")
public class Role extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -6525302831793188081L;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

}
