package com.codesimple.bookstore.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codesimple.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book>findAllByYearOfPublicationIn(Set<Integer> yop);
	List<Book> findAllByBookType(String bookType);
	List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop, String bookType);
	
	String rawQuery = "select * from book where year_of_publication In :y_o_p ";
	
	@Query(nativeQuery = true, value = rawQuery)
	List<Book> findAllByYearOfPublicationIns(@Param("y_o_p") Set<Integer> yop);
	

	

}
