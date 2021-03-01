package ${package}.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity(name = "fixed_expense")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FixedExpense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fee_type_id", referencedColumnName = "id")
	private FeeType feeType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_type_id", referencedColumnName = "id")
	private CategoryType categoryType;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "charge_day")
	private Integer chargeDay;

	@Column(name = "charge_quantity")
	private Double chargeQuantity;

	private boolean active;

	private String description;

}