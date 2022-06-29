package com.category.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CategoryServiceApplicationTests {

  @DisplayName("컨텍스트 로딩 테스트")
  @Test
  void contextLoad() {
    Assertions.assertTrue(true);
  }

}
