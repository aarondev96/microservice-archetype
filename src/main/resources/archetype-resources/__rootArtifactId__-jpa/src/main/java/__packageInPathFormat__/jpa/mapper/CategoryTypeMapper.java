package ${package}.jpa.mapper;

import ${package}.jpa.dto.CategoryTypeDto;
import ${package}.jpa.entity.CategoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryTypeMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTypeMapper.class);

	public CategoryTypeDto categoryTypeToCategoryTypeDto(CategoryType categoryType) {
		LOGGER.trace(">> categoryTypeToCategoryTypeDto() categoryType {}", categoryType);

		if (categoryType == null || categoryType.getId() == null) {
			LOGGER.warn("<< categoryTypeToCategoryTypeDto() categoryType or categoryType.id is null");
			return null;
		}
		CategoryTypeDto categoryTypeDto = CategoryTypeDto.builder()
				.id(categoryType.getId())
				.category(categoryType.getCategory())
				.build();

		LOGGER.trace("<< categoryTypeToCategoryTypeDto() categoryTypeDto {}", categoryTypeDto);
		return categoryTypeDto;
	}

	public List<CategoryTypeDto> categoryTypeListToCategoryTypeDtoList(List<CategoryType> categoryTypeList) {
		LOGGER.trace(">> categoryTypeListToCategoryTypeDtoList() categoryTypeList {}", categoryTypeList);

		if (categoryTypeList == null || categoryTypeList.isEmpty()) {
			LOGGER.warn("<< categoryTypeListToCategoryTypeDtoList() categoryTypeList is null or categoryTypeList is empty");
			return new ArrayList<>();
		}
		List<CategoryTypeDto> categoryTypeDtoList = new ArrayList<>();
		for (CategoryType currentCategoryType : categoryTypeList) {
			categoryTypeDtoList.add(categoryTypeToCategoryTypeDto(currentCategoryType));
		}

		LOGGER.trace("<< categoryTypeListToCategoryTypeDtoList() categoryTypeDtoList {}", categoryTypeDtoList);
		return categoryTypeDtoList;
	}

	public CategoryType categoryTypeDtoToCategoryType(CategoryTypeDto categoryTypeDto) {
		LOGGER.trace(">> categoryTypeDtoToCategoryType() categoryTypeDto {}", categoryTypeDto);

		if (categoryTypeDto == null) {
			LOGGER.warn("<< categoryTypeToCategoryTypeDto() categoryTypeDto null");
			return null;
		}
		CategoryType categoryType = CategoryType.builder()
				.id(categoryTypeDto.getId())
				.category(categoryTypeDto.getCategory())
				.build();

		LOGGER.trace("<< categoryTypeDtoToCategoryType() categoryType {}", categoryType);
		return categoryType;
	}

	public List<CategoryType> categoryTypeDtoListToCategoryTypeList(List<CategoryTypeDto> categoryTypeDtoList) {
		LOGGER.trace(">> categoryTypeDtoListToCategoryTypeList() categoryTypeDtoList {}", categoryTypeDtoList);

		if (categoryTypeDtoList == null || categoryTypeDtoList.isEmpty()) {
			LOGGER.warn("<< categoryTypeDtoListToCategoryTypeList() categoryTypeDtoList is null or categoryTypeDtoList is empty");
			return new ArrayList<>();
		}
		List<CategoryType> categoryTypeList = new ArrayList<>();
		for (CategoryTypeDto currentCategoryTypeDto : categoryTypeDtoList) {
			categoryTypeList.add(categoryTypeDtoToCategoryType(currentCategoryTypeDto));
		}

		LOGGER.trace("<< categoryTypeDtoListToCategoryTypeList() categoryTypeList {}", categoryTypeList);
		return categoryTypeList;
	}

}
