package ${package}.jpa.repository;

import ${package}.jpa.entity.PaymentNumberType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentNumberTypeRepository extends CrudRepository<PaymentNumberType, Long> {

	Optional<PaymentNumberType> findByNumber(Integer number);

}