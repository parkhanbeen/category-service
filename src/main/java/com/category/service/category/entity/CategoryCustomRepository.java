package com.category.service.category.entity;

import java.util.List;

public interface CategoryCustomRepository {

  /**
   * 전체 카테고리를 반환합니다.
   */
  List<Category> findCategories();

  /**
   * 카테고리 식별자를 이용해 해당 카테고리 정보와 하위 카테고리 정보를 반환합니다.
   *
   * @param id 카테고리 식별자
   */
  List<Category> findCategoriesById(Long id);
}
