package com.category.service.category.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryCustomRepositoryHelper {

  public List<Category> createCategoriesById() {
    var parentCategories = createParentCategories();
    var childCategories = createChildCategories();
    childCategories.forEach(parentCategories.get(0)::addChild);
    return List.of(parentCategories.get(0));
  }

  public List<Category> createCategories() {
    var parentCategories = createParentCategories();
    var childCategories = createChildCategories();
    childCategories.forEach(parentCategories.get(0)::addChild);
    return parentCategories;
  }

  private List<Category> createParentCategories() {
    return List.of(
        Category.builder()
            .name("카테고리1")
            .sort(1)
            .build(),
        Category.builder()
            .name("카테고리2")
            .sort(2)
            .build(),
        Category.builder()
            .name("카테고리3")
            .sort(3)
            .build()
    );
  }

  private List<Category> createChildCategories() {
    return List.of(
        Category.builder()
            .name("카테고리1-1")
            .sort(3)
            .build(),
        Category.builder()
            .name("카테고리1-2")
            .sort(2)
            .build(),
        Category.builder()
            .name("카테고리1-3")
            .sort(1)
            .build()
    );
  }
}
