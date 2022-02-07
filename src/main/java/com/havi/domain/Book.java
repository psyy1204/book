package com.havi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
//생성자 자동생성(파라미터가 없는 생성자를 생성함)
@Getter
//단순히 필드를 리턴하는것 필드 이름이 book라면 메소드이름은 getBook(class적용시 전체필드에 적용됨)
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue
    private Integer idx;

    @Column
    private String title;

    @Column
    private LocalDateTime publishedAt;

    @Builder
    //해당 클래스의 빌더패턴 클래스를 생성해줌
    public Book(String title, LocalDateTime publishedAt) {
        this.title = title;
        this.publishedAt = publishedAt;
    }
}
