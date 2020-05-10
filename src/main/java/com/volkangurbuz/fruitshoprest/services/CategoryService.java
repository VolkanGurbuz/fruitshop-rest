package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

  List<CategoryDTO> getAllCategories();

  CategoryDTO getCategoryByName(String name);
}
