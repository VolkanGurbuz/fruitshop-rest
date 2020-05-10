package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.mapper.CategoryMapper;
import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.domain.Category;
import com.volkangurbuz.fruitshoprest.repositories.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

  CategoryService categoryService;

  @Mock CategoryRepository categoryRepository;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
  }

  @Test
  public void getAllCategories() throws Exception {
    // given
    List<Category> categoryList = Arrays.asList(new Category(), new Category(), new Category());

    when(categoryRepository.findAll()).thenReturn(categoryList);

    // when
    List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

    // then
    assertEquals(3, categoryDTOS.size());
  }

  @Test
  public void getCategoryByName() throws Exception {

    // given
    Category category = new Category();
    category.setId(2L);
    category.setName("test");

    when(categoryRepository.findByName(anyString())).thenReturn(category);

    // when
    CategoryDTO categoryDTO = categoryService.getCategoryByName("test");
    // then
    assertEquals(2L, categoryDTO.getId());
    assertEquals("test", categoryDTO.getName());
  }
}
