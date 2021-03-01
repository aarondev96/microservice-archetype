package ${package}.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "card")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "card_number")
	private String cardNumber;

}