package ${package}.jpa.repository;

import ${package}.jpa.entity.CategoryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryTypeRepository extends CrudRepository<CategoryType, Long> {

	Optional<CategoryType> findByCategory(String category);

}