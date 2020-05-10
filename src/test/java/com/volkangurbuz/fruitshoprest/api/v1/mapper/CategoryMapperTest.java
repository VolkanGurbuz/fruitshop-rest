package com.volkangurbuz.fruitshoprest.api.v1.mapper;

import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

  CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  @Test
  public void categoryToCategoryDTO() {
    Category cat = new Category();
    cat.setName("test");
    cat.setId(1L);

    // when
    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(cat);

    // then
    assertEquals(Long.valueOf(1L), categoryDTO.getId());
    assertEquals("test", categoryDTO.getName());
  }
}
