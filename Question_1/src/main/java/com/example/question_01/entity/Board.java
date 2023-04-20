package com.example.question_01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

//entity
@Getter
@NoArgsConstructor
public class Board implements Comparable<Board> {
    @Setter
    private Long id = -1L;    //게시판 번호

    private Set<Category> categories = new LinkedHashSet<>(); //게시판 카테고리의 가장 하위 카테고리

    public Board(Category category) {
        this.categories.add(category);
    }
    /*
    1. 게시판의 가장 하위 카테고리 1개만, 알고 있으면
    자료구조에 의해 상위 카테고리 모두를 알 수 있다.
    2. List의 형태로 카테고리를 가지고 있다면,
    추후 게시판의 카테고리를 변경하는것이 매우 곤란해진다.
    그래서 유지보수 측면에서 최하위 카테고리 1개만 저장하는 것이 좋다
    3.하나의 최하위 카테고리는 하나의 게시판을 가질 수 있다.
    4.반대로, 하나의 게시판은 여러개의 카테고리를 가질 수 있다.
    * */

    public void addCategory(Category category) {
        this.categories.add(category);
    }


    //게시판 id의 순서가 필요하므로, TreeMap으로 구현
    @Override
    public int compareTo(Board otherBoard) {
        // ID를 기준으로 오름차순 정렬을 한다고 가정했을 때
        return this.id.compareTo(otherBoard.getId());
    }

}
