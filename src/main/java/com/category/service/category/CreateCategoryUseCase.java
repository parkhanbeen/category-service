package com.category.service.category;

import com.category.service.category.entity.Category;

/**
 * 카테고리 생성 UseCase.
 */
public interface CreateCategoryUseCase {

  Category create(CreateCategoryCommand command);
}
