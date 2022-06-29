package com.category.service.category;

import com.category.service.category.entity.Category;

/**
 * 카테고리 수정 UseCase.
 */
public interface UpdateCategoryUseCase {

  /**
   * 카테고리 수정 후 수정된 카테고리 정보를 반환합니다.
   *
   * @param categoryId 카테고리 식별자
   * @param command    수정할 카테고리 정보
   */
  Category update(long categoryId, UpdateCategoryCommand command);
}
