package com.category.service.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.category.service.category.entity.Category;
import com.category.service.category.entity.CategoryRepository;
import com.category.service.category.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("CreateCategoryService 테스트")
@ExtendWith(MockitoExtension.class)
class CreateCategoryServiceTest {

  @InjectMocks
  private CreateCategoryService createCategoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @Nested
  @DisplayName("create 메서드는")
  class DescribeOf_create {
    final Long givenId = 1L;
    final String givenName = "카테고리1";
    final int givenSort = 1;

    @Nested
    @DisplayName("카테고리 정보가 주어질 경우")
    class ContextWith_createCategoryByCreateCommand {
      @BeforeEach
      void setUp() {
        var givenCategory = Category.builder()
            .name(givenName)
            .sort(givenSort)
            .build();

        given(categoryRepository.save(any()))
            .willReturn(givenCategory);

        ReflectionTestUtils.setField(givenCategory, "id", givenId);
      }

      private CreateCategoryCommand subject() {
        return CreateCategoryCommand.builder()
            .name(givenName)
            .sort(givenSort)
            .build();
      }

      @Test
      @DisplayName("카테고리를 생성한 후 카테고리 정보를 반환한다.")
      void it_returns() {
        // given
        var command = subject();

        // when
        var result = createCategoryService.create(command);

        // then
        assertThat(result.getId()).isEqualTo(givenId);
        assertThat(result.getName()).isEqualTo(givenName);
        assertThat(result.getSort()).isEqualTo(givenSort);
      }
    }

    @Nested
    @DisplayName("찾을수 없는 부모 카테고리 식별자가 주어질 경우")
    class ContextWith_createCategoryByParentId {

      private CreateCategoryCommand subject() {
        return CreateCategoryCommand.builder()
            .parentId(givenId)
            .name(givenName)
            .sort(givenSort)
            .build();
      }

      @Test
      @DisplayName("예외를 반환한다.")
      void it_exception() {
        // given
        final var command = subject();

        // when
        Throwable thrown = catchThrowable(() -> createCategoryService.create(command));

        // then
        assertThat(thrown).isInstanceOf(CategoryNotFoundException.class)
            .hasMessage("등록된 카테고리가 없습니다. [카테고리 식별자 : 1]");
      }
    }

  }

}
