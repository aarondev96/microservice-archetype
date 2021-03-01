package ${package}.jpa.mapper;

import ${package}.jpa.dto.PaymentNumberTypeDto;
import ${package}.jpa.entity.PaymentNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentNumberTypeMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentNumberTypeMapper.class);

	public PaymentNumberTypeDto paymentNumberTypeToPaymentNumberTypeDto(PaymentNumberType paymentNumberType) {
		LOGGER.trace(">> paymentNumberTypeToPaymentNumberTypeDto() paymentNumberType {}", paymentNumberType);

		if (paymentNumberType == null || paymentNumberType.getId() == null) {
			LOGGER.warn("<< paymentNumberTypeToPaymentNumberTypeDto() paymentNumberType or paymentNumberType.id is null");
			return null;
		}
		PaymentNumberTypeDto paymentNumberTypeDto = PaymentNumberTypeDto.builder()
				.id(paymentNumberType.getId())
				.number(paymentNumberType.getNumber())
				.build();

		LOGGER.trace("<< paymentNumberTypeToPaymentNumberTypeDto() paymentNumberTypeDto {}", paymentNumberTypeDto);
		return paymentNumberTypeDto;
	}

	public List<PaymentNumberTypeDto> paymentNumberTypeListToPaymentNumberTypeDtoList(List<PaymentNumberType> paymentNumberTypeList) {
		LOGGER.trace(">> paymentNumberTypeListToPaymentNumberTypeDtoList() paymentNumberTypeList {}", paymentNumberTypeList);

		if (paymentNumberTypeList == null || paymentNumberTypeList.isEmpty()) {
			LOGGER.warn("<< paymentNumberTypeListToPaymentNumberTypeDtoList() paymentNumberTypeList is null or paymentNumberTypeList is empty");
			return new ArrayList<>();
		}
		List<PaymentNumberTypeDto> paymentNumberTypeDtoList = new ArrayList<>();
		for (PaymentNumberType currentPaymentNumberType : paymentNumberTypeList) {
			paymentNumberTypeDtoList.add(paymentNumberTypeToPaymentNumberTypeDto(currentPaymentNumberType));
		}

		LOGGER.trace("<< paymentNumberTypeListToPaymentNumberTypeDtoList() paymentNumberTypeDtoList {}", paymentNumberTypeDtoList);
		return paymentNumberTypeDtoList;
	}

	public PaymentNumberType paymentNumberTypeDtoToPaymentNumberType(PaymentNumberTypeDto paymentNumberTypeDto) {
		LOGGER.trace(">> paymentNumberTypeDtoToPaymentNumberType() paymentNumberTypeDto {}", paymentNumberTypeDto);

		if (paymentNumberTypeDto == null) {
			LOGGER.warn("<< paymentNumberTypeToPaymentNumberTypeDto() paymentNumberTypeDto null");
			return null;
		}
		PaymentNumberType paymentNumberType = PaymentNumberType.builder()
				.id(paymentNumberTypeDto.getId())
				.number(paymentNumberTypeDto.getNumber())
				.build();

		LOGGER.trace("<< paymentNumberTypeDtoToPaymentNumberType() paymentNumberType {}", paymentNumberType);
		return paymentNumberType;
	}

	public List<PaymentNumberType> paymentNumberTypeDtoListToPaymentNumberTypeList(List<PaymentNumberTypeDto> paymentNumberTypeDtoList) {
		LOGGER.trace(">> paymentNumberTypeDtoListToPaymentNumberTypeList() paymentNumberTypeDtoList {}", paymentNumberTypeDtoList);

		if (paymentNumberTypeDtoList == null || paymentNumberTypeDtoList.isEmpty()) {
			LOGGER.warn("<< paymentNumberTypeDtoListToPaymentNumberTypeList() paymentNumberTypeDtoList is null or paymentNumberTypeDtoList is empty");
			return new ArrayList<>();
		}
		List<PaymentNumberType> paymentNumberTypeList = new ArrayList<>();
		for (PaymentNumberTypeDto currentPaymentNumberTypeDto : paymentNumberTypeDtoList) {
			paymentNumberTypeList.add(paymentNumberTypeDtoToPaymentNumberType(currentPaymentNumberTypeDto));
		}

		LOGGER.trace("<< paymentNumberTypeDtoListToPaymentNumberTypeList() paymentNumberTypeList {}", paymentNumberTypeList);
		return paymentNumberTypeList;
	}

}
