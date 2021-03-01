package ${package}.jpa.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PayrollDto {

	private Long id;
	private AccountDto account;
	private Double paycheck;
	private Double extraPayment;
	private Integer paymentNumber;
	private Date annualGrossSalary;

}