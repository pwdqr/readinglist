package com.study.readlinglist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.readlinglist.domain.Book;

/*
 * JpaRepository�� Ȯ���Ͽ� 18���� ���� �޼��� ���.
 * JapRepository �������̽��� ù��° �Ű������� �������丮�� ����� ������ Ÿ���̰�
 * �ι�° �Ű������� Ŭ������ ID ������Ƽ Ÿ��.
 * 
 * ������ �����ʹ� �������丮�� �������̽��� ���Ǹ� �ص� ������ �� �ֵ��� �Ѵ�.
 * �������̽��� ��Ÿ��(���ø����̼��� ������ ��)�� �ڵ����� �����ȴ�.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(String reader); // ������ ���� �̸����� ���� ��� �˻�
}
