package ${package}.jpa.repository;

import ${package}.jpa.entity.Payroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Long> {

}