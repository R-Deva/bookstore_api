package com.codesimple.bookstore.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.dto.BookDTO;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books")
	public List<Book> getBook(@RequestParam (value = "yearOfPublications", required = false) Set<Integer> yop,
			@RequestParam (value = "bookType",required = false) String bookType){
		return bookService.getBook(yop,bookType);

	}


	@RequestMapping(value = "/books",method = RequestMethod.POST)
	public Book createBook(@RequestBody Book book)
	{
		return bookService.createBook(book);
	}

	
	
	

	@RequestMapping(value = "/books/{id}")
	public BookDTO getBookById(@PathVariable (name = "id") Long bookId,
			@RequestParam (value = "authorData",required = false) boolean authorData)
	{
		return bookService.getBookById(bookId,authorData);

	}


	@RequestMapping(value = "/books", method = RequestMethod.PUT)
	public Book updateBook(@RequestBody Book incommingBook)
	{
		return bookService.updateBook(incommingBook);
	}


	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
	public String deleteBookById(@PathVariable Long bookId)
	{
		return bookService.deleteBookById(bookId);	
	}


	@GetMapping("/raw/books")
	public APIResponse getBooksByRawQuery(@RequestParam("yop") Set<Integer> yop)
	{
		return bookService.getBookByRawQuery(yop);	
	}
	
	
	@GetMapping("/caughtException")
	public APIResponse getCaughtException(@RequestParam(value = "number") Integer yop)
	{
		return bookService.getCoughtException(yop);	
	}
	
	

}
