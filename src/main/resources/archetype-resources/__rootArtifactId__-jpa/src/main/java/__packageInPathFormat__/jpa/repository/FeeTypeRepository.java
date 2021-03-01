package ${package}.jpa.repository;

import ${package}.jpa.entity.FeeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeeTypeRepository extends CrudRepository<FeeType, Long> {

	Optional<FeeType> findByType(String type);

}