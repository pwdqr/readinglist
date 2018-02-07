package com.study.readlinglist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.readlinglist.domain.Book;

/*
 * JpaRepository를 확장하여 18개의 공통 메서드 상속.
 * JapRepository 인터페이스의 첫번째 매개변수는 리포지토리가 사용할 도메인 타입이고
 * 두번째 매개변수는 클래스의 ID 프러퍼티 타입.
 * 
 * 스프링 데이터는 리포지토리를 인터페이스로 정의만 해도 동작할 수 있도록 한다.
 * 인터페이스는 런타임(어플리케이션이 시작할 때)에 자동으로 구현된다.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(String reader); // 지정한 독자 이름으로 독서 목록 검색
}
