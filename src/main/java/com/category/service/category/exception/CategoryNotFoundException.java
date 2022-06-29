package com.category.service.category.exception;

public class CategoryNotFoundException extends RuntimeException {

  private static final String MESSAGE = "등록된 카테고리가 없습니다. [카테고리 식별자 : %s]";

  public CategoryNotFoundException(long categoryId) {
    super(String.format(MESSAGE, categoryId));
  }
}
