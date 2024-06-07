package com.codesimple.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codesimple.bookstore.entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{

}
