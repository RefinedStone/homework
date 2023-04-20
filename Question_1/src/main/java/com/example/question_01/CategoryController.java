package com.example.question_01;

import com.example.question_01.CategoryDto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories") //복수형
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /* 카테고리 검색
    1. 카테고리 식별자 id 또는 카테고리명으로 검색
    2. 반환값은 하위 카테고리를 모두 담고 있음
    */

    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(required = false) Long id, @RequestParam(required = false) String title) {
        return categoryService.getCategories(id, title);
    }

    /* 카테고리 생성
    1. 새로운 카테고리 생성을 위해 카테고리명 입력
    */
    @PostMapping
    public ResponseEntity<?> createCategories(@RequestParam(required = true) String title) {
        return categoryService.createCategories(title);
    }

    /*카테고리 변경
    1. 중간에 새 카테고리를 삽입할 경우, ex) 1-2 사이에 3을 넣는다면, 1-3-2의 구조가 됨
    2. 상위 카테고리를 입력하지 않으면, root카테고리의 하위 카테고리로 등록됨.
    3. 하위 카테고리를 입력하지 않으면, 최하위 카테고리로 입력됨.
    4. 상위,하위 카테고리를 입력하지 않으면 exception 발생
    * */
    @PatchMapping
    public ResponseEntity<?> setRelation(@RequestParam(required = false) Long parentId, @RequestParam(required = true) Long newCategoryId, @RequestParam(required = false) Long childId) {
        return categoryService.setRelation(parentId, newCategoryId, childId);
    }


}
