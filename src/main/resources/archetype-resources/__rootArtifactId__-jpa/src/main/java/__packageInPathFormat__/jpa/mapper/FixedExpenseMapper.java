package ${package}.jpa.mapper;

import ${package}.jpa.dto.FixedExpenseDto;
import ${package}.jpa.entity.CategoryType;
import ${package}.jpa.entity.FeeType;
import ${package}.jpa.entity.FixedExpense;
import ${package}.jpa.repository.CategoryTypeRepository;
import ${package}.jpa.repository.FeeTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FixedExpenseMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(FixedExpenseMapper.class);

	private final AccountMapper accountMapper;
	private final FeeTypeRepository feeTypeRepository;
	private final CategoryTypeRepository categoryTypeRepository;

	public FixedExpenseMapper(AccountMapper accountMapper, FeeTypeRepository feeTypeRepository, CategoryTypeRepository categoryTypeRepository) {
		this.accountMapper = accountMapper;
		this.feeTypeRepository = feeTypeRepository;
		this.categoryTypeRepository = categoryTypeRepository;
	}

	public FixedExpenseDto fixedExpenseToFixedExpenseDto(FixedExpense fixedExpense) {
		LOGGER.trace(">> fixedExpenseToFixedExpenseDto() fixedExpense {}", fixedExpense);

		if (fixedExpense == null || fixedExpense.getId() == null) {
			LOGGER.warn("<< fixedExpenseToFixedExpenseDto() fixedExpense or fixedExpense.id is null");
			return null;
		}
		FixedExpenseDto fixedExpenseDto = FixedExpenseDto.builder()
				.id(fixedExpense.getId())
				.active(fixedExpense.isActive())
				.account(accountMapper.accountToAccountDto(fixedExpense.getAccount()))
				.endDate(fixedExpense.getEndDate())
				.startDate(fixedExpense.getStartDate())
				.chargeDay(fixedExpense.getChargeDay())
				.description(fixedExpense.getDescription())
				.description(fixedExpense.getDescription())
				.feeType(fixedExpense.getFeeType().getType())
				.chargeQuantity(fixedExpense.getChargeQuantity())
				.category(fixedExpense.getCategoryType().getCategory())
				.build();

		LOGGER.trace("<< fixedExpenseToFixedExpenseDto() fixedExpenseDto {}", fixedExpenseDto);
		return fixedExpenseDto;
	}

	public List<FixedExpenseDto> fixedExpenseListToFixedExpenseDtoList(List<FixedExpense> fixedExpenseList) {
		LOGGER.trace(">> fixedExpenseListToFixedExpenseDtoList() fixedExpenseList {}", fixedExpenseList);

		if (fixedExpenseList == null || fixedExpenseList.isEmpty()) {
			LOGGER.warn("<< fixedExpenseListToFixedExpenseDtoList() fixedExpenseList is null or fixedExpenseList is empty");
			return new ArrayList<>();
		}
		List<FixedExpenseDto> fixedExpenseDtoList = new ArrayList<>();
		for (FixedExpense currentFixedExpense : fixedExpenseList) {
			fixedExpenseDtoList.add(fixedExpenseToFixedExpenseDto(currentFixedExpense));
		}

		LOGGER.trace("<< fixedExpenseListToFixedExpenseDtoList() fixedExpenseDtoList {}", fixedExpenseDtoList);
		return fixedExpenseDtoList;
	}

	public FixedExpense fixedExpenseDtoToFixedExpense(FixedExpenseDto fixedExpenseDto) {
		LOGGER.trace(">> fixedExpenseDtoToFixedExpense() fixedExpenseDto {}", fixedExpenseDto);

		if (fixedExpenseDto == null) {
			LOGGER.warn("<< fixedExpenseDtoToFixedExpense() fixedExpenseDto null");
			return null;
		}
		FixedExpense fixedExpense = FixedExpense.builder()
				.id(fixedExpenseDto.getId())
				.active(fixedExpenseDto.isActive())
				.account(accountMapper.accountDtoToAccount(fixedExpenseDto.getAccount()))
				.endDate(fixedExpenseDto.getEndDate())
				.startDate(fixedExpenseDto.getStartDate())
				.chargeDay(fixedExpenseDto.getChargeDay())
				.description(fixedExpenseDto.getDescription())
				.description(fixedExpenseDto.getDescription())
				.chargeQuantity(fixedExpenseDto.getChargeQuantity())
				.build();
		Optional<FeeType> feeType = feeTypeRepository.findByType(fixedExpenseDto.getFeeType());
		feeType.ifPresent(fixedExpense::setFeeType);
		Optional<CategoryType> categoryType = categoryTypeRepository.findByCategory(fixedExpenseDto.getCategory());
		categoryType.ifPresent(fixedExpense::setCategoryType);

		LOGGER.trace("<< fixedExpenseDtoToFixedExpense() fixedExpense {}", fixedExpense);
		return fixedExpense;
	}

	public List<FixedExpense> fixedExpenseDtoListToFixedExpenseList(List<FixedExpenseDto> fixedExpenseDtoList) {
		LOGGER.trace(">> fixedExpenseDtoListToFixedExpenseList() fixedExpenseDtoList {}", fixedExpenseDtoList);

		if (fixedExpenseDtoList == null || fixedExpenseDtoList.isEmpty()) {
			LOGGER.warn("<< fixedExpenseDtoListToFixedExpenseList() fixedExpenseDtoList is null or fixedExpenseDtoList is empty");
			return new ArrayList<>();
		}
		List<FixedExpense> fixedExpenseList = new ArrayList<>();
		for (FixedExpenseDto currentFixedExpenseDto : fixedExpenseDtoList) {
			fixedExpenseList.add(fixedExpenseDtoToFixedExpense(currentFixedExpenseDto));
		}

		LOGGER.trace("<< fixedExpenseDtoListToFixedExpenseList() fixedExpenseList {}", fixedExpenseList);
		return fixedExpenseList;
	}

}
