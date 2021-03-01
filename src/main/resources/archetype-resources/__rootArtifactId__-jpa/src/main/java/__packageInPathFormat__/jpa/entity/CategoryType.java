package ${package}.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "category_type")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CategoryType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	private String category;

}