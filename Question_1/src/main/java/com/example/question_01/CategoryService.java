package com.example.question_01;

import com.example.question_01.CategoryDto.CategoryDto;
import com.example.question_01.entity.Category;
import com.example.question_01.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /*카테고리 조회*/
    public List<CategoryDto> getCategories(Long id, String title) {
        if (id == null && title == null) {
            throw new IllegalArgumentException("카테고리 id 혹은 카테고리명 중 하나만 입력 해주세요.");
        }

        if (id != null && title != null) {
            throw new IllegalArgumentException("카테고리 id 혹은 카테고리명 중 하나를 입력 해주세요.");
        }

        return title != null ? getCategories(title) : getCategories(id);
    }

    /*카테고리 생성*/
    public ResponseEntity<?> createCategories(String title) {
        return ResponseEntity.ok(categoryRepository.create(new Category(title)));
    }

    /*카테고리 상위,하위 관계 변경*/
    public ResponseEntity<?> setRelation(Long parentId, Long newCategoryId, Long childId) {
        categoryRepository.setRelation(parentId, newCategoryId, childId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    //extracted method
    public List<CategoryDto> getCategories(Long id) {
        List<CategoryDto> list = new ArrayList<>();
        list.add(new CategoryDto(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없습니다."))));
        return list;
    }

    public List<CategoryDto> getCategories(String title) {
        List<Category> categoryList = categoryRepository.findByTitle(title);

        if (categoryList.size() == 0)
            throw new RuntimeException("해당 카테고리를 찾을 수 없습니다.");
        else
            return categoryList.stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
