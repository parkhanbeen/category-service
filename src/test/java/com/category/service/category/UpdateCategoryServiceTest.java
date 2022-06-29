package com.category.service.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("UpdateCategoryService 테스트")
@ExtendWith(MockitoExtension.class)
class UpdateCategoryServiceTest {

  @InjectMocks
  UpdateCategoryService updateCategoryService;

  @Mock
  private CategoryRepository categoryRepository;

  final Long givenId = 1L;
  final String givenName = "카테고리1";
  final int givenSort = 1;

  @BeforeEach
  void setUp() {
    var givenCategory = Category.builder()
        .name(givenName)
        .sort(givenSort)
        .build();

    given(categoryRepository.findById(anyLong()))
        .willReturn(Optional.of(givenCategory));

    ReflectionTestUtils.setField(givenCategory, "id", givenId);
  }


  @Nested
  @DisplayName("update 메서드는")
  class DescribeOf_update {

    final String changedName = "변경된 카테고리1";
    final int changedSort = 2;

    @Nested
    @DisplayName("카테고리 정보가 주어질 경우")
    class ContextWith_updateCategoryByUpdateCommand {
      private UpdateCategoryCommand subject() {
        return UpdateCategoryCommand.builder()
            .name(changedName)
            .sort(changedSort)
            .build();
      }

      @Test
      @DisplayName("카테고리를 수정한 후 카테고리 정보를 반환한다.")
      void it_returns() {
        // given
        var command = subject();

        // when
        var result = updateCategoryService.update(givenId, command);

        // then
        assertThat(result.getId()).isEqualTo(givenId);
        assertThat(result.getName()).isEqualTo(changedName);
        assertThat(result.getSort()).isEqualTo(changedSort);
      }
    }
  }

}