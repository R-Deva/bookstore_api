package com.codesimple.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Service;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.common.PaginationMeta;
import com.codesimple.bookstore.data.AuthorData;
import com.codesimple.bookstore.entity.Author;
import com.codesimple.bookstore.repo.AuthorRepo;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepo;

	public APIResponse getAuthors(@SortDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		APIResponse apiResponse = new APIResponse();
		
		Page<Author> authorpage = authorRepo.findAll(pageable);
		
		List<Author> authors = authorpage.getContent();
		PaginationMeta authorPaginationMeta = PaginationMeta.createPagination(authorpage);
		
		AuthorData authorData = new AuthorData();
		authorData.setAuthors(authors);
		authorData.setPagination(authorPaginationMeta);
		
		apiResponse.setData(authorData);
		return apiResponse;
	}

}
