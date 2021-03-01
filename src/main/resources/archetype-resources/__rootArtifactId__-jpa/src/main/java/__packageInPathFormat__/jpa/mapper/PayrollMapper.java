package ${package}.jpa.mapper;

import ${package}.jpa.dto.PayrollDto;
import ${package}.jpa.entity.PaymentNumberType;
import ${package}.jpa.entity.Payroll;
import ${package}.jpa.repository.PaymentNumberTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PayrollMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollMapper.class);

	private final AccountMapper accountMapper;
	private final PaymentNumberTypeRepository paymentNumberTypeRepository;

	public PayrollMapper(AccountMapper accountMapper, PaymentNumberTypeRepository paymentNumberTypeRepository) {
		this.accountMapper = accountMapper;
		this.paymentNumberTypeRepository = paymentNumberTypeRepository;
	}

	public PayrollDto payrollToPayrollDto(Payroll payroll) {
		LOGGER.trace(">> payrollToPayrollDto() payroll {}", payroll);

		if (payroll == null || payroll.getId() == null) {
			LOGGER.warn("<< payrollToPayrollDto() payroll or payroll.id is null");
			return null;
		}
		PayrollDto payrollDto = PayrollDto.builder()
				.id(payroll.getId())
				.account(accountMapper.accountToAccountDto(payroll.getAccount()))
				.paycheck(payroll.getPaycheck())
				.extraPayment(payroll.getExtraPayment())
				.annualGrossSalary(payroll.getAnnualGrossSalary())
				.paymentNumber(payroll.getPaymentNumberType().getNumber())
				.build();

		LOGGER.trace("<< payrollToPayrollDto() payrollDto {}", payrollDto);
		return payrollDto;
	}

	public List<PayrollDto> payrollListToPayrollDtoList(List<Payroll> payrollList) {
		LOGGER.trace(">> payrollListToPayrollDtoList() payrollList {}", payrollList);

		if (payrollList == null || payrollList.isEmpty()) {
			LOGGER.warn("<< payrollListToPayrollDtoList() payrollList is null or payrollList is empty");
			return new ArrayList<>();
		}
		List<PayrollDto> payrollDtoList = new ArrayList<>();
		for (Payroll currentPayroll : payrollList) {
			payrollDtoList.add(payrollToPayrollDto(currentPayroll));
		}

		LOGGER.trace("<< payrollListToPayrollDtoList() payrollDtoList {}", payrollDtoList);
		return payrollDtoList;
	}

	public Payroll payrollDtoToPayroll(PayrollDto payrollDto) {
		LOGGER.trace(">> payrollDtoToPayroll() payrollDto {}", payrollDto);

		if (payrollDto == null) {
			LOGGER.warn("<< payrollToPayrollDto() payrollDto null");
			return null;
		}
		Payroll payroll = Payroll.builder()
				.id(payrollDto.getId())
				.account(accountMapper.accountDtoToAccount(payrollDto.getAccount()))
				.paycheck(payrollDto.getPaycheck())
				.extraPayment(payrollDto.getExtraPayment())
				.annualGrossSalary(payrollDto.getAnnualGrossSalary())
				.build();
		Optional<PaymentNumberType> paymentNumberType = paymentNumberTypeRepository.findByNumber(payrollDto.getPaymentNumber());
		paymentNumberType.ifPresent(payroll::setPaymentNumberType);

		LOGGER.trace("<< payrollDtoToPayroll() payroll {}", payroll);
		return payroll;
	}

	public List<Payroll> payrollDtoListToPayrollList(List<PayrollDto> payrollDtoList) {
		LOGGER.trace(">> payrollDtoListToPayrollList() payrollDtoList {}", payrollDtoList);

		if (payrollDtoList == null || payrollDtoList.isEmpty()) {
			LOGGER.warn("<< payrollDtoListToPayrollList() payrollDtoList is null or payrollDtoList is empty");
			return new ArrayList<>();
		}
		List<Payroll> payrollList = new ArrayList<>();
		for (PayrollDto currentPayrollDto : payrollDtoList) {
			payrollList.add(payrollDtoToPayroll(currentPayrollDto));
		}

		LOGGER.trace("<< payrollDtoListToPayrollList() payrollList {}", payrollList);
		return payrollList;
	}

}
