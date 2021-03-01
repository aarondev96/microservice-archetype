package ${package}.jpa.repository;

import ${package}.jpa.entity.Variable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableRepository extends CrudRepository<Variable, Long> {

}