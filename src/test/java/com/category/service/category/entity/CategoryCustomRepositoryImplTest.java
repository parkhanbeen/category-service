package com.category.service.category.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.category.service.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("CategoryCustomRepositoryImpl 테스트")
@Import(TestConfig.class)
@DataJpaTest
class CategoryCustomRepositoryImplTest {

  final CategoryCustomRepositoryHelper helper = new CategoryCustomRepositoryHelper();

  @Autowired
  CategoryRepository categoryRepository;

  @Nested
  @DisplayName("findCategories 메서드는")
  class DescribeOf_findCategories {

    @BeforeEach
    void setUp() {
      categoryRepository.saveAll(helper.createCategories());
    }

    @AfterEach
    void after() {
      categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("모든 카테고리 정보를 반환한다.")
    void it_returns() {

      // when
      var result = categoryRepository.findCategories();

      // then
      assertThat(result).isNotNull();
      assertThat(result.size()).isEqualTo(3);
      assertThat(result.get(0).getName()).isEqualTo("카테고리1");
      assertThat(result.get(0).getChildren().size()).isEqualTo(3);
      assertThat(result.get(0).getChildren().get(0).getName()).isEqualTo("카테고리1-1");
      assertThat(result.get(0).getChildren().get(2).getSort()).isEqualTo(1);
      assertThat(result.get(2).getSort()).isEqualTo(3);
    }
  }

}
