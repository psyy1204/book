package com.havi.repository;

import com.havi.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
//데이터 검색을 할 수 있는 구조
//인터페이스에 미리 검색 메소드를 정의 해 두는 것으로, 메소드 호출하는 것만으로 스마트한 데이터 검색을 할 수 있다
// /import org.springframework.stereotype.Repository; 자동적으로 사라짐????

public interface BookRepository extends JpaRepository<Book, Integer> {
                                                    //엔티티클래스이름, ID타입
}
