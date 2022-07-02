package com.category.service.category.entity;

import java.util.List;

public interface CategoryCustomRepository {

  /**
   * 전체 카테고리를 반환합니다.
   */
  List<Category> findCategories();
}
