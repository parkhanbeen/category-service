package com.category.service.category.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.category.service.common.entity.BaseDateTimeEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Table
@Entity
public class Category extends BaseDateTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 카테고리 명.
   */
  @Column(nullable = false)
  private String name;

  /**
   * 순서.
   */
  @Column(nullable = false)
  private Integer sort;

  /**
   * 부모 카테고리.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  /**
   * 자식 카테고리.
   */
  @OneToMany(mappedBy = "parent")
  private List<Category> children = new ArrayList<>();

  protected Category() {
  }

  @Builder
  public Category(String name,
                  int sort) {
    this.name = name;
    this.sort = sort;
  }

  /**
   * 자식 카테고리를 추가합니다.
   *
   * @param child 자식 카테고리
   */
  public void addChild(Category child) {
    this.children.add(child);
    child.addParent(this);
  }

  private void addParent(Category parent) {
    this.parent = parent;
  }

  /**
   * 카테고리 정보를 변경한 후 변경된 카테고리를 반환합니다.
   */
  public Category update(String name, int sort) {
    this.name = name;
    this.sort = sort;
    return this;
  }

}
