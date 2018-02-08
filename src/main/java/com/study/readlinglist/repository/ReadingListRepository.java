package com.study.readlinglist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.readlinglist.domain.Book;
import com.study.readlinglist.domain.Reader;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(Reader reader);
}
