package com.category.service.category;

/**
 * 카테고리 삭제 UseCase.
 */
public interface DeleteCategoryUseCase {

  /**
   * 카테고리 식별자를 통해 카테고리를 삭제합니다.
   *
   * @param categoryId 카테고리 식별자
   */
  long delete(long categoryId);
}
