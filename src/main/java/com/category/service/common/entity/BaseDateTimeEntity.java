package com.category.service.common.entity;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTimeEntity {

  /**
   * 생성일시.
   */
  @CreatedDate
  private LocalDateTime createdDateTime;

  /**
   * 수정일시.
   */
  @LastModifiedDate
  private LocalDateTime modifiedDateTime;

}
