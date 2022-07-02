package com.category.service.category;

import com.category.service.category.entity.CategoryRepository;
import com.category.service.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCategoryService implements DeleteCategoryUseCase {

  private final CategoryRepository categoryRepository;

  @Transactional
  @Override
  public long delete(long categoryId) {
    var findCategory = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException(categoryId));

    categoryRepository.delete(findCategory);
    return categoryId;
  }
}
