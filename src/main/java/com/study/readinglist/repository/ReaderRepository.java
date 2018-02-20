package com.study.readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.readinglist.domain.Reader;

/*
 * ReadingRepository와 마찬가지로 ReaderRepository 또한 추가로 구현 코드를
 * 작성할 필요가 없다.
 * JpaRepository를 확장하므로 스프링 데이터 JPA가 런타임에 자동으로 구현체를
 * 생성한다.
 * 결국 Reader 엔티티를 다루는 메세드 18개를 자동으로 생성한다.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {

}
