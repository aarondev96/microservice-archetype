package ${package}.jpa.repository;

import ${package}.jpa.entity.FixedExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedExpenseRepository extends CrudRepository<FixedExpense, Long> {

}