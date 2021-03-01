package ${package}.jpa.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VariableDto {

	private Long id;
	private Date date;
	private Double quantity;
	private AccountDto account;
	private String category;
	private String description;
	private boolean expense = true;

}