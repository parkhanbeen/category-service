package com.category.service.category;

import java.util.List;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindCategoryService implements FindCategoryUseCase {

  private final CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Category> findCategories() {
    return categoryRepository.findCategories();
  }

  @Transactional(readOnly = true)
  @Override
  public List<Category> findCategoriesById(Long id) {
    return categoryRepository.findCategoriesById(id);
  }
}
