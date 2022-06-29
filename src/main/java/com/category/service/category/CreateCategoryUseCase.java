package com.category.service.category;

import com.category.service.category.entity.Category;

/**
 * 카테고리 생성 UseCase.
 */
public interface CreateCategoryUseCase {

  /**
   * 카테고리를 생성한 후 생성된 카테고리를 반환합니다.
   *
   * @param command 생성할 카테고리 정보
   */
  Category create(CreateCategoryCommand command);
}
