package com.category.service.utils;

import static org.springframework.restdocs.snippet.Attributes.key;

import org.springframework.restdocs.snippet.Attributes;

public interface RestDocFormatGenerator {
  static Attributes.Attribute getDateTimeFormat() {
    return key("format").value("yyyy-MM-dd'T'HH:mm:ss.SSS+09:00");
  }
}
