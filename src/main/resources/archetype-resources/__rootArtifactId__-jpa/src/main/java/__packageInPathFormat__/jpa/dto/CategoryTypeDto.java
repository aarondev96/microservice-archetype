package ${package}.jpa.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTypeDto {

	private Long id;
	private String category;

}