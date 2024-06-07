package com.codesimple.bookstore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codesimple.bookstore.common.Error;
import com.codesimple.bookstore.entity.Book;

@Component
public class BookValidator {

	public List<Error> validateCreateBookRequest(Book book) {
		
		List<Error> errors = new ArrayList();
		if(book.getName()==null)
		{
			Error error = new Error("name","Book name is null");
			errors.add(error);
		}
		if(book.getYearOfPublication()==null)
		{
			Error error = new Error("Yop","Yop is null");
			errors.add(error);
		}
		
		if(book.getBookType()==null)
		{
			Error error = new Error("BookType","BookType is null");
			errors.add(error);
		}
		return errors;
		
		
	}

}
