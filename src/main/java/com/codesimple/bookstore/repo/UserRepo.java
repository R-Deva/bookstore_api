package com.codesimple.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codesimple.bookstore.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}
