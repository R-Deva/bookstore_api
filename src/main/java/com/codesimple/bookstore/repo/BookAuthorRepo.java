package com.codesimple.bookstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codesimple.bookstore.entity.BookAuthor;

@Repository
public interface BookAuthorRepo extends JpaRepository<BookAuthor, Long>{

	List<BookAuthor>findAllByBookId(Long bookId);
}
