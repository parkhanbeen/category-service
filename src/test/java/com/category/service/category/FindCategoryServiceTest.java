package com.category.service.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.List;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryCustomRepositoryHelper;
import com.category.service.category.entity.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("FindCategoryService 테스트")
@ExtendWith(MockitoExtension.class)
class FindCategoryServiceTest {

  final CategoryCustomRepositoryHelper helper = new CategoryCustomRepositoryHelper();

  @InjectMocks
  private FindCategoryService findCategoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @Nested
  @DisplayName("findCategories 메서드는")
  class DescribeOf_findCategories {

    @Test
    @DisplayName("모든 카테고리 정보를 반환한다.")
    void it_returns() {
      // given
      given(categoryRepository.findCategories())
          .willReturn(helper.createCategories());

      // when
      List<Category> categories = findCategoryService.findCategories();

      // then
      assertThat(categories).isNotEmpty();
      assertThat(categories.size()).isEqualTo(3);
      assertThat(categories.get(0).getChildren().size()).isEqualTo(3);
    }
  }

  @Nested
  @DisplayName("findCategoriesById 메서드는")
  class DescribeOf_findCategoriesById {

    @Test
    @DisplayName("주어진 카테고리 식별자 맞는 카테고리와 하위 카테고리만을 반환한다.")
    void it_returns() {
      // given
      final var givenId = 1L;
      given(categoryRepository.findCategoriesById(givenId))
          .willReturn(helper.createCategoriesById());

      // when
      List<Category> categories = findCategoryService.findCategoriesById(givenId);

      // then
      assertThat(categories).isNotEmpty();
      assertThat(categories.size()).isEqualTo(1);
      assertThat(categories.get(0).getChildren().size()).isEqualTo(3);
    }
  }

}
