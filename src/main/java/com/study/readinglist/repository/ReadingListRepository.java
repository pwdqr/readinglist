package com.study.readinglist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.readinglist.domain.Book;
import com.study.readinglist.domain.Reader;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(Reader reader);
}
