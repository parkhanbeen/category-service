package com.category.service.category;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
import com.category.service.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateCategoryService implements UpdateCategoryUseCase {

  private final CategoryRepository categoryRepository;

  @Transactional
  @Override
  public Category update(long categoryId,
                         UpdateCategoryCommand command) {

    var findCategory = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException(categoryId));

    return findCategory.update(command.getName(), command.getSort());
  }
}
