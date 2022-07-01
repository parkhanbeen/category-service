package com.category.service.category;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
import com.category.service.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCategoryService implements CreateCategoryUseCase {

  private final CategoryRepository categoryRepository;

  @Transactional
  @Override
  public Category create(CreateCategoryCommand command) {

    if (command.isRootCategory()) {
      return categoryRepository.save(command.toEntity());
    }

    long parentId = command.getParentId();

    var parentCategory = categoryRepository.findById(parentId)
        .orElseThrow(() -> new CategoryNotFoundException(parentId));

    var createdCategory = categoryRepository.save(command.toEntity());
    parentCategory.addChild(createdCategory);

    return createdCategory;
  }
}
