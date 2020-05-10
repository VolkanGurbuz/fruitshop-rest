package com.volkangurbuz.fruitshoprest.controller;

import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.controller.v1.CategoryController;
import com.volkangurbuz.fruitshoprest.domain.Category;
import com.volkangurbuz.fruitshoprest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

  public static final String NAME = "Jim";

  @Mock CategoryService categoryService;
  // going to do is autimatically inject that category service into the contnroller
  @InjectMocks CategoryController categoryController;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    // we do not need this anymore
    // categoryController = new CategoryController(categoryService);

    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
  }

  @Test
  public void testListCategories() throws Exception {
    CategoryDTO category1 = new CategoryDTO();
    category1.setId(1L);
    category1.setName(NAME);

    CategoryDTO category2 = new CategoryDTO();
    category2.setId(2L);
    category2.setName("bob");

    List<CategoryDTO> categoryDTOList = Arrays.asList(category1, category2);

    when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

    mockMvc
        .perform(get("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.categories", hasSize(2)));
  }

  @Test
  public void testGetByNameCategories() throws Exception {
    CategoryDTO category1 = new CategoryDTO();
    category1.setId(1l);
    category1.setName(NAME);

    when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

    mockMvc
        .perform(get("/api/v1/categories/Jim").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", equalTo(NAME)));
  }
}