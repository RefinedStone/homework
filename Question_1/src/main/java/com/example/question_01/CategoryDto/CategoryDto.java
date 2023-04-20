package com.example.question_01.CategoryDto;


import com.example.question_01.entity.Board;
import com.example.question_01.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    public Long category_id;
    public String title;
    public List<CategoryDto> children;
    public Long board_id;

    public CategoryDto(Category category) {
        this.category_id = category.getId();
        this.title = category.getTitle();
        this.children = category.getChildren().stream().map(CategoryDto::new).collect(Collectors.toList());
        this.board_id = Optional.ofNullable(category.getBoard())
                .map(Board::getId)
                .orElse(null);
    }
}
