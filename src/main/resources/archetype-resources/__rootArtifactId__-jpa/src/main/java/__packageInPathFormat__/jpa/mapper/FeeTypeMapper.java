package ${package}.jpa.mapper;

import ${package}.jpa.dto.FeeTypeDto;
import ${package}.jpa.entity.FeeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeeTypeMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeeTypeMapper.class);

	public FeeTypeDto feeTypeToFeeTypeDto(FeeType feeType) {
		LOGGER.trace(">> feeTypeToFeeTypeDto() feeType {}", feeType);

		if (feeType == null || feeType.getId() == null) {
			LOGGER.warn("<< feeTypeToFeeTypeDto() feeType or feeType.id is null");
			return null;
		}
		FeeTypeDto feeTypeDto = FeeTypeDto.builder()
				.id(feeType.getId())
				.type(feeType.getType())
				.build();

		LOGGER.trace("<< feeTypeToFeeTypeDto() feeTypeDto {}", feeTypeDto);
		return feeTypeDto;
	}

	public List<FeeTypeDto> feeTypeListToFeeTypeDtoList(List<FeeType> feeTypeList) {
		LOGGER.trace(">> feeTypeListToFeeTypeDtoList() feeTypeList {}", feeTypeList);

		if (feeTypeList == null || feeTypeList.isEmpty()) {
			LOGGER.warn("<< feeTypeListToFeeTypeDtoList() feeTypeList is null or feeTypeList is empty");
			return new ArrayList<>();
		}
		List<FeeTypeDto> feeTypeDtoList = new ArrayList<>();
		for (FeeType currentFeeType : feeTypeList) {
			feeTypeDtoList.add(feeTypeToFeeTypeDto(currentFeeType));
		}

		LOGGER.trace("<< feeTypeListToFeeTypeDtoList() feeTypeDtoList {}", feeTypeDtoList);
		return feeTypeDtoList;
	}

	public FeeType feeTypeDtoToFeeType(FeeTypeDto feeTypeDto) {
		LOGGER.trace(">> feeTypeDtoToFeeType() feeTypeDto {}", feeTypeDto);

		if (feeTypeDto == null) {
			LOGGER.warn("<< feeTypeToFeeTypeDto() feeTypeDto null");
			return null;
		}
		FeeType feeType = FeeType.builder()
				.id(feeTypeDto.getId())
				.type(feeTypeDto.getType())
				.build();

		LOGGER.trace("<< feeTypeDtoToFeeType() feeType {}", feeType);
		return feeType;
	}

	public List<FeeType> feeTypeDtoListToFeeTypeList(List<FeeTypeDto> feeTypeDtoList) {
		LOGGER.trace(">> feeTypeDtoListToFeeTypeList() feeTypeDtoList {}", feeTypeDtoList);

		if (feeTypeDtoList == null || feeTypeDtoList.isEmpty()) {
			LOGGER.warn("<< feeTypeDtoListToFeeTypeList() feeTypeDtoList is null or feeTypeDtoList is empty");
			return new ArrayList<>();
		}
		List<FeeType> feeTypeList = new ArrayList<>();
		for (FeeTypeDto currentFeeTypeDto : feeTypeDtoList) {
			feeTypeList.add(feeTypeDtoToFeeType(currentFeeTypeDto));
		}

		LOGGER.trace("<< feeTypeDtoListToFeeTypeList() feeTypeList {}", feeTypeList);
		return feeTypeList;
	}

}
