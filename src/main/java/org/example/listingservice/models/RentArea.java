package org.example.listingservice.models;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "rentarea")
public class RentArea extends BaseEntity {
	@Column(name = "value")
	private Long value;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="buildingid")
	private Building building;
}
