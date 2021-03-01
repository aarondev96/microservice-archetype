package ${package}.jpa.mapper;

import ${package}.jpa.dto.AccountDto;
import ${package}.jpa.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountMapper.class);

	private final LoanMapper loanMapper;
	private final PayrollMapper payrollMapper;
	private final VariableMapper variableMapper;
	private final FixedExpenseMapper fixedExpenseMapper;

	public AccountMapper(LoanMapper loanMapper, PayrollMapper payrollMapper, VariableMapper variableMapper, FixedExpenseMapper fixedExpenseMapper) {
		this.loanMapper = loanMapper;
		this.payrollMapper = payrollMapper;
		this.variableMapper = variableMapper;
		this.fixedExpenseMapper = fixedExpenseMapper;
	}

	public AccountDto accountToAccountDto(Account account) {
		LOGGER.trace(">> accountToAccountDto() account {}", account);

		if (account == null || account.getId() == null) {
			LOGGER.warn("<< accountToAccountDto() account or account.id is null");
			return null;
		}
		AccountDto accountDto = AccountDto.builder()
				.id(account.getId())
				.balance(account.getBalance())
				.loanList(loanMapper.loanListToLoanDtoList(account.getLoanList()))
				.payrollList(payrollMapper.payrollListToPayrollDtoList(account.getPayrollList()))
				.variableList(variableMapper.variableListToVariableDtoList(account.getVariableList()))
				.fixedExpenseList(fixedExpenseMapper.fixedExpenseListToFixedExpenseDtoList(account.getFixedExpenseList()))
				.build();

		LOGGER.trace("<< accountToAccountDto() accountDto {}", accountDto);
		return accountDto;
	}

	public List<AccountDto> accountListToAccountDtoList(List<Account> accountList) {
		LOGGER.trace(">> accountListToAccountDtoList() accountList {}", accountList);

		if (accountList == null || accountList.isEmpty()) {
			LOGGER.warn("<< accountListToAccountDtoList() accountList is null or accountList is empty");
			return new ArrayList<>();
		}
		List<AccountDto> accountDtoList = new ArrayList<>();
		for (Account currentAccount : accountList) {
			accountDtoList.add(accountToAccountDto(currentAccount));
		}

		LOGGER.trace("<< accountListToAccountDtoList() accountDtoList {}", accountDtoList);
		return accountDtoList;
	}

	public Account accountDtoToAccount(AccountDto accountDto) {
		LOGGER.trace(">> accountDtoToAccount() accountDto {}", accountDto);

		if (accountDto == null) {
			LOGGER.warn("<< accountToAccountDto() accountDto null");
			return null;
		}
		Account account = Account.builder()
				.id(accountDto.getId())
				.balance(accountDto.getBalance())
				.loanList(loanMapper.loanDtoListToLoanList(accountDto.getLoanList()))
				.payrollList(payrollMapper.payrollDtoListToPayrollList(accountDto.getPayrollList()))
				.variableList(variableMapper.variableDtoListToVariableList(accountDto.getVariableList()))
				.fixedExpenseList(fixedExpenseMapper.fixedExpenseDtoListToFixedExpenseList(accountDto.getFixedExpenseList()))
				.build();

		LOGGER.trace("<< accountDtoToAccount() account {}", account);
		return account;
	}

	public List<Account> accountDtoListToAccountList(List<AccountDto> accountDtoList) {
		LOGGER.trace(">> accountDtoListToAccountList() accountDtoList {}", accountDtoList);

		if (accountDtoList == null || accountDtoList.isEmpty()) {
			LOGGER.warn("<< accountDtoListToAccountList() accountDtoList is null or accountDtoList is empty");
			return new ArrayList<>();
		}
		List<Account> accountList = new ArrayList<>();
		for (AccountDto currentAccountDto : accountDtoList) {
			accountList.add(accountDtoToAccount(currentAccountDto));
		}

		LOGGER.trace("<< accountDtoListToAccountList() accountList {}", accountList);
		return accountList;
	}

}
