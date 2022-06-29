package com.category.service.web.category;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

import java.io.IOException;

import com.category.service.category.entity.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.util.ResourceUtils;

public class CategoryMockHelper {

  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
  }

  public Category createCategory() {
    return Category.builder()
        .name("카테고리1")
        .sort(1)
        .build();
  }

  public CreateCategoryResponse createCategoryResponse(Category category) {
    return new TestCreateCategoryResponse(category);
  }

  public String createCategoryRequest() throws IOException {
    return objectMapper.writeValueAsString(
        objectMapper.readValue(
            ResourceUtils.getFile(CLASSPATH_URL_PREFIX +
                "mock/category/create_category_request.json"), CreateCategoryRequest.class));
  }
}

class TestCreateCategoryResponse extends CreateCategoryResponse {

  TestCreateCategoryResponse(Category category) {
    super(category.getId(), category.getName(), category.getSort(), category.getCreatedDateTime());
  }
}