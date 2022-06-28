package com.category.service.category;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
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
    return categoryRepository.save(command.toEntity());
  }
}
