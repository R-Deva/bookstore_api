package com.codesimple.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.service.AuthorService;

@RestController
public class AuthorController {

	@Autowired
	public AuthorService authorService;
	
	@GetMapping("/authors")
	private  APIResponse getAuthor(Pageable pageable) {
		
		APIResponse apiResponse = authorService.getAuthors(pageable);
		
		return apiResponse;
	}
}
