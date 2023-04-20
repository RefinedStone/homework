package com.example.question_01.repository;

import com.example.question_01.entity.Board;
import com.example.question_01.entity.Category;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BoardRepositoryImpl implements BoardRepository {
    private static final String ANONYMOUS_CATEGORY = "익명게시판";
    private Long id;
    //relation -> Map을 통해 트리구조를 구현한다.
    //게시판 id의 순서가 필요하므로, TreeMap으로 구현
    private Map<Long, Board> data;

    public BoardRepositoryImpl() {
        this.id = 1L;
        this.data = new TreeMap<>();
    }

    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void create(Board board) {
        board.setId(id);
        data.put(id, board);
        board.getCategories().forEach(category -> category.setBoard(board));
        id++;
    }

    @Override
    public void addCategoryToBoard(Long boardId, Category category) {
        if (!category.getTitle().equals(ANONYMOUS_CATEGORY))
            throw new RuntimeException("오직 익명게시판만이 여러 카테고리를 가질 수 있습니다");

        Board board = data.computeIfAbsent(boardId, e -> {
            throw new RuntimeException("존재하지 않는 게시판입니다.");
        });

        board.addCategory(category);
        category.setBoard(board);
    }

    @Override
    public void update(Long boardId, Category category) {
        Board board = data.computeIfAbsent(boardId, e -> {
            throw new RuntimeException("존재하지 않는 게시판입니다.");
        });

        board.getCategories().clear(); //게시판의 카테고리 삭제
        board.getCategories().add(category);  //새로운 카테고리 추가
        category.setBoard(board); //반대로 카테고리에도 게시판 추가
    }
}
