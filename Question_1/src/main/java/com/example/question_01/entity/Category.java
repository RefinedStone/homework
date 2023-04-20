package com.example.question_01.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Setter
    private Long id = -1L;    //카테고리 번호
    @Setter
    private String title;   //카테고리 제목
    private List<Category> children = new LinkedList<>(); //자식 카테고리
    @Setter
    private Board board; //게시판

    /*
    1.카테고리는 tree형 자료구조의 노드로서 사용된다
    2.노드와 노드와의 관계는 repository에서 정립
    3.하나의 최하위 카테고리는 하나의 게시판을 가질 수 있다.
    4.반대로, 하나의 게시판은 여러개의 카테고리를 가질 수 있다.
    */

    public Category(String title) {
        this.title = title;
    }

    public void addChild(Category child) {
        this.children.add(child);
    }

    public void deleteChild(Category child) {
        this.children.remove(child);
    }

}
