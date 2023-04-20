package com.example.question_01.repository;

import com.example.question_01.entity.Board;
import com.example.question_01.entity.Category;

import java.util.Optional;

public interface BoardRepository {

    Optional<Board> findById(Long id);

    void create(Board board);

    void update(Long boardId, Category category);

    void addCategoryToBoard(Long boardId, Category category);
}
