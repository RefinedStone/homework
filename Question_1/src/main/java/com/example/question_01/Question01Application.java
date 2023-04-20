package com.example.question_01;

import com.example.question_01.repository.BoardRepository;
import com.example.question_01.entity.Board;
import com.example.question_01.entity.Category;
import com.example.question_01.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Question01Application {

    public static void main(String[] args) {
        SpringApplication.run(Question01Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepository, BoardRepository boardRepository) {
        return args -> {
            //root-> 카테고리 자료구조의 최상위 카테고리
            Category root = categoryRepository.findById(0L).orElseThrow(() -> new RuntimeException("root 카테고리를 찾을 수 없습니다."));

            /*주어진 카테고리 예시*/
            //남자 카테고리 생성
            Category category1 = new Category("남자");
            categoryRepository.create(category1);
            //남자 카테고리의 상위 카테고리(root) 지정
            categoryRepository.save(root, category1);


            // 엑소 카테고리 생성
            Category category2 = new Category("엑소");
            categoryRepository.create(category2);
            //엑소 카테고리의 상위 카테고리(남자) 지정
            categoryRepository.save(category1, category2);

            // 엑소-공지사항 카테고리 생성
            Category notice1 = new Category("공지사항");
            categoryRepository.create(notice1);
            //공지사항 카테고리의 상위 카테고리(엑소) 지정
            categoryRepository.save(category2, notice1);
            //게시판 생성
            boardRepository.create(new Board(notice1));

            //첸 카테고리 생성
            Category category3 = new Category("첸");
            categoryRepository.create(category3);
            //첸 카테고리의 상위 카테고리(엑소) 지정
            categoryRepository.save(category2, category3);
            //게시판 생성
            boardRepository.create(new Board(category3));

            //백현 카테고리 생성
            Category category4 = new Category("백현");
            categoryRepository.create(category4);
            //백현 카테고리의 상위 카테고리(엑소) 지정
            categoryRepository.save(category2, category4);
            //게시판 생성
            boardRepository.create(new Board(category4));

            //시우민 카테고리 생성
            Category category5 = new Category("시우민");
            categoryRepository.create(category5);
            //시우민 카테고리의 상위 카테고리(엑소) 지정
            categoryRepository.save(category2, category5);
            //게시판 생성
            boardRepository.create(new Board(category5));

            //방탄소년단 카테고리 생성
            Category category6 = new Category("방탄소년단");
            categoryRepository.create(category6);
            //방탄소년단 카테고리의 상위 카테고리(남자) 지정
            categoryRepository.save(category1, category6);

            // 방탄소년단-공지사항 카테고리 생성
            Category notice2 = new Category("공지사항");
            categoryRepository.create(notice2);
            //공지사항 카테고리의 상위 카테고리(방탄소년단) 지정
            categoryRepository.save(category6, notice2);
            //게시판 생성
            boardRepository.create(new Board(notice2));

            // 방탄소년단-익명게시판 카테고리 생성
            Category anony1 = new Category("익명게시판");
            categoryRepository.create(anony1);
            //익명게시판 카테고리의 상위 카테고리(방탄소년단) 지정
            categoryRepository.save(category6, anony1);
            //게시판 생성
            boardRepository.create(new Board(anony1));

            //뷔 카테고리 생성
            Category category7 = new Category("뷔");
            categoryRepository.create(category7);
            //뷔 카테고리의 상위 카테고리(방탄소년단) 지정
            categoryRepository.save(category6, category7);
            //게시판 생성
            boardRepository.create(new Board(category7));

            //여자 카테고리 생성
            Category category8 = new Category("여자");
            categoryRepository.create(category8);
            //여자 카테고리의 상위 카테고리(root) 지정
            categoryRepository.save(root, category8);

            //블랙핑크 카테고리 생성
            Category category9 = new Category("블랙핑크");
            categoryRepository.create(category9);
            //블랙핑크 카테고리의 상위 카테고리(여자) 지정
            categoryRepository.save(category8, category9);

            // 블랙핑크-공지사항 카테고리 생성
            Category notice3 = new Category("공지사항");
            categoryRepository.create(notice3);
            //공지사항 카테고리의 상위 카테고리(블랙핑크) 지정
            categoryRepository.save(category9, notice3);
            //게시판 생성
            boardRepository.create(new Board(notice3));

            // 블랙핑크-익명 카테고리 생성
            Category anony2 = new Category("익명게시판");
            categoryRepository.create(anony2);
            //익명게시판 카테고리의 상위 카테고리(블랙핑크) 지정
            categoryRepository.save(category9, anony2);
            //게시판에 카테고리 추가
            boardRepository.addCategoryToBoard(6L, anony2);

            //로제 카테고리 생성
            Category category10 = new Category("로제");
            categoryRepository.create(category10);
            //로제 카테고리의 상위 카테고리(블랙핑크) 지정
            categoryRepository.save(category9, category10);
            //게시판 생성
            boardRepository.create(new Board(category10));

        };

    }
}
