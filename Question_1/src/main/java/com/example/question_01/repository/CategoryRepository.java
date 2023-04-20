package com.example.question_01.repository;

import com.example.question_01.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository {
    Long create(Category category);

    void save(Category parentId, Category newId);

    void setRelation(Long parentId, Long newId, Long childId);

    /*중간에 카테고리를 삽입할 경우, ex) 1-2 사이에 3을 넣는다면, 1-3-2의 구조가 됨*/
    void update(Category parent, Category newCategory, Category child);

    void delete(Long parentId, Long childId);

    List<Category> findByTitle(String title);

    Optional<Category> findById(Long id);

    Set<Category> getChildren(Category parent);
}
