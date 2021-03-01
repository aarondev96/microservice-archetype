package ${package}.jpa.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FeeTypeDto {

	private Long id;
	private String type;

}