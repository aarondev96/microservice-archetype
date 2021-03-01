package ${package}.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity(name = "payroll")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_number_type_id", referencedColumnName = "id")
	private PaymentNumberType paymentNumberType;

	@Column(name = "paycheck")
	private Double paycheck;

	@Column(name = "extra_payment")
	private Double extraPayment;

	@Column(name = "annual_gross_salary")
	private Date annualGrossSalary;

}