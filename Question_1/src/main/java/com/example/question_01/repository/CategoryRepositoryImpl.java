package com.example.question_01.repository;


import com.example.question_01.entity.Category;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {
    //relation -> Map을 통해 트리구조를 구현한다.
    private Map<Category, Set<Category>> relation = new HashMap<>();
    //idToCategory -> indexing의 개념으로, 해당 index로 시간복잡도(1)로 접근하게 해준다.
    private Map<Long, Category> idToCategory = new HashMap<>();
    //titleToCategory -> indexing의 개념으로, 해당 title로 시간복잡도(1)로 접근하게 해준다.
    private Map<String, List<Category>> titleToCategory = new HashMap<>();
    private Long id;

    public CategoryRepositoryImpl() {
        this.id = 1L;
        Category root = new Category("root"); //최상위 카테고리인 root node설정
        root.setId(0L);
        relation.put(root, new HashSet<>());
        idToCategory.put(root.getId(), root);
        titleToCategory.computeIfAbsent(root.getTitle(), k -> new ArrayList<>()).add(root);
    }

    @Override
    public Long create(Category newCategory) {
        newCategory.setId(id);
        relation.put(newCategory, new HashSet<>());
        idToCategory.put(newCategory.getId(), newCategory);
        titleToCategory.computeIfAbsent(newCategory.getTitle(), k -> new ArrayList<>()).add(newCategory);
        return id++;
    }

    @Override
    public void save(Category parent, Category newCategory) {
        relation.get(parent).add(newCategory);
        parent.addChild(newCategory);
    }

    @Override
    public void setRelation(Long parentId, Long newId, Long childId) {
        Category parent = idToCategory.getOrDefault(parentId, null);
        Category newCategory = idToCategory.getOrDefault(newId, null);
        Category child = idToCategory.getOrDefault(childId, null);

        if (child == null) {
            //1. parent가 null이면서, child가 null일때
            if (parent == null) {
                throw new NullPointerException("최소한 상위카테고리,하위카테고리 중 하나를 입력해야 합니다.");
            }
            //2. parent가 not null이면서, child가 null일때
            else {
                save(parent, newCategory);
            }
        } else {
            Category parentToUpdate = parent != null ? parent : idToCategory.get(0L);
            // 3. parent가 null이면서, child가 not null일때
            // 4. parent가 not null이면서, child가 not null일때
            update(parentToUpdate, newCategory, child);
        }
    }

    /*중간에 카테고리를 삽입할 경우, ex) 1-2 사이에 3을 넣는다면, 1-3-2의 구조가 됨*/
    @Override
    public void update(Category parent, Category newCategory, Category child) {
        Set<Category> orDefault = relation.get(parent);
        orDefault.remove(child);
        orDefault.add(newCategory);
        parent.deleteChild(child);
        parent.addChild(newCategory);
        save(newCategory, child);
    }

    @Override
    public void delete(Long parentId, Long childId) {
        Category parent = idToCategory.get(parentId);
        Category child = idToCategory.get(childId);
        relation.get(parent).remove(child);
        parent.deleteChild(child);
    }

    public List<Category> findByTitle(String title) {
        return titleToCategory.getOrDefault(title, Collections.emptyList());
    }

    public Set<Category> getChildren(Category parent) {
        return relation.get(parent);
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(idToCategory.get(id));
    }
}

