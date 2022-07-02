package com.category.service.category;

import java.util.List;

import com.category.service.category.entity.Category;

/**
 * 전체 카테고리 조회 UseCase.
 */
public interface FindCategoryUseCase {

  /**
   * 전체 카테고리 정보를 반환합니다.
   */
  List<Category> findCategories();
}
