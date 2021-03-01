package ${package}.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity(name = "variable")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Variable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_type_id", referencedColumnName = "id")
	private CategoryType categoryType;

	private Date date;

	private Double quantity;

	private String description;

	private boolean expense = true;

}