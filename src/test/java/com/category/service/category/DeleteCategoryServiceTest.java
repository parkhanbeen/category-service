package com.category.service.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("CreateCategoryService 테스트")
@ExtendWith(MockitoExtension.class)
class DeleteCategoryServiceTest {

  @InjectMocks
  private DeleteCategoryService deleteCategoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @Nested
  @DisplayName("delete 메서드는")
  class DescribeOf_create {
    final Long givenId = 1L;
    final String givenName = "카테고리1";
    final int givenSort = 1;

    @Nested
    @DisplayName("카테고리 식별자가 주어질 경우")
    class ContextWith_createCategoryByCreateCommand {
      @BeforeEach
      void setUp() {
        final var givenCategory = Category.builder()
            .name(givenName)
            .sort(givenSort)
            .build();

        given(categoryRepository.findById(anyLong()))
            .willReturn(Optional.of(givenCategory));

        ReflectionTestUtils.setField(givenCategory, "id", givenId);
      }

      @Test
      @DisplayName("해당 해당 카테고리를 삭제한다.")
      void it_returns() {
        // given
        final long givenId = 1L;

        // when
        deleteCategoryService.delete(givenId);

        // then
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).delete(categoryArgumentCaptor.capture());
      }
    }
  }

}
