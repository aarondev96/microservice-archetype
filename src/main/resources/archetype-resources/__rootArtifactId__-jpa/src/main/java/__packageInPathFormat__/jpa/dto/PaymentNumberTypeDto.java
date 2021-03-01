package ${package}.jpa.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentNumberTypeDto {

	private Long id;
	private Integer number;

}