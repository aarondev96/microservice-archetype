package ${package}.jpa.mapper;

import ${package}.jpa.dto.LoanDto;
import ${package}.jpa.entity.CategoryType;
import ${package}.jpa.entity.FeeType;
import ${package}.jpa.entity.Loan;
import ${package}.jpa.repository.CategoryTypeRepository;
import ${package}.jpa.repository.FeeTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoanMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoanMapper.class);

	private final AccountMapper accountMapper;
	private final FeeTypeRepository feeTypeRepository;
	private final CategoryTypeRepository categoryTypeRepository;

	public LoanMapper(AccountMapper accountMapper, FeeTypeRepository feeTypeRepository, CategoryTypeRepository categoryTypeRepository) {
		this.accountMapper = accountMapper;
		this.feeTypeRepository = feeTypeRepository;
		this.categoryTypeRepository = categoryTypeRepository;
	}

	public LoanDto loanToLoanDto(Loan loan) {
		LOGGER.trace(">> loanToLoanDto() loan {}", loan);

		if (loan == null || loan.getId() == null) {
			LOGGER.warn("<< loanToLoanDto() loan or loan.id is null");
			return null;
		}
		LoanDto loanDto = LoanDto.builder()
				.id(loan.getId())
				.active(loan.isActive())
				.endDate(loan.getEndDate())
				.account(accountMapper.accountToAccountDto(loan.getAccount()))
				.chargeDay(loan.getChargeDay())
				.startDate(loan.getStartDate())
				.feeType(loan.getFeeType().getType())
				.totalQuantity(loan.getTotalQuantity())
				.chargeQuantity(loan.getChargeQuantity())
				.category(loan.getCategoryType().getCategory())
				.build();

		LOGGER.trace("<< loanToLoanDto() loanDto {}", loanDto);
		return loanDto;
	}

	public List<LoanDto> loanListToLoanDtoList(List<Loan> loanList) {
		LOGGER.trace(">> loanListToLoanDtoList() loanList {}", loanList);

		if (loanList == null || loanList.isEmpty()) {
			LOGGER.warn("<< loanListToLoanDtoList() loanList is null or loanList is empty");
			return new ArrayList<>();
		}
		List<LoanDto> loanDtoList = new ArrayList<>();
		for (Loan currentLoan : loanList) {
			loanDtoList.add(loanToLoanDto(currentLoan));
		}

		LOGGER.trace("<< loanListToLoanDtoList() loanDtoList {}", loanDtoList);
		return loanDtoList;
	}

	public Loan loanDtoToLoan(LoanDto loanDto) {
		LOGGER.trace(">> loanDtoToLoan() loanDto {}", loanDto);

		if (loanDto == null) {
			LOGGER.warn("<< loanToLoanDto() loanDto null");
			return null;
		}
		Loan loan = Loan.builder()
				.id(loanDto.getId())
				.active(loanDto.isActive())
				.endDate(loanDto.getEndDate())
				.account(accountMapper.accountDtoToAccount(loanDto.getAccount()))
				.chargeDay(loanDto.getChargeDay())
				.startDate(loanDto.getStartDate())
				.totalQuantity(loanDto.getTotalQuantity())
				.chargeQuantity(loanDto.getChargeQuantity())
				.build();
		Optional<FeeType> feeType = feeTypeRepository.findByType(loanDto.getFeeType());
		feeType.ifPresent(loan::setFeeType);
		Optional<CategoryType> categoryType = categoryTypeRepository.findByCategory(loanDto.getCategory());
		categoryType.ifPresent(loan::setCategoryType);

		LOGGER.trace("<< loanDtoToLoan() loan {}", loan);
		return loan;
	}

	public List<Loan> loanDtoListToLoanList(List<LoanDto> loanDtoList) {
		LOGGER.trace(">> loanDtoListToLoanList() loanDtoList {}", loanDtoList);

		if (loanDtoList == null || loanDtoList.isEmpty()) {
			LOGGER.warn("<< loanDtoListToLoanList() loanDtoList is null or loanDtoList is empty");
			return new ArrayList<>();
		}
		List<Loan> loanList = new ArrayList<>();
		for (LoanDto currentLoanDto : loanDtoList) {
			loanList.add(loanDtoToLoan(currentLoanDto));
		}

		LOGGER.trace("<< loanDtoListToLoanList() loanList {}", loanList);
		return loanList;
	}

}
