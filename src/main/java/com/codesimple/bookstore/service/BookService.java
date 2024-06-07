package com.codesimple.bookstore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.common.BadRequestException;
import com.codesimple.bookstore.common.Error;
import com.codesimple.bookstore.data.BookData;
import com.codesimple.bookstore.dto.AuthorDTO;
import com.codesimple.bookstore.dto.BookDTO;
import com.codesimple.bookstore.entity.Author;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.entity.BookAuthor;
import com.codesimple.bookstore.repo.BookAuthorRepo;
import com.codesimple.bookstore.repo.BookRepository;
import com.codesimple.bookstore.validator.BookValidator;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookAuthorRepo bookAuthorRepo;

	@Autowired
	private BookValidator bookValidator;

	public List<Book> getBook(Set<Integer> yop, String bookType){
		List<Book> booklist = new ArrayList();

		if(yop==null && bookType == null)
		{
			bookRepository.findAll().forEach(book->booklist.add(book));
		}
		else if(yop!=null && bookType==null){
			return bookRepository.findAllByYearOfPublicationIn(yop);
		}
		else if(yop==null && bookType!=null){
			return bookRepository.findAllByBookType(bookType);
		}
		else {
			return bookRepository.findAllByYearOfPublicationInAndBookType(yop,bookType);
		}
		return booklist;	
	}


	public Book createBook(Book book)
	{
		List<Error> errors = bookValidator.validateCreateBookRequest(book);
		if(errors.size()>0) {
			throw new BadRequestException("bad request",errors);
		}
		return bookRepository.save(book);
	}


	public BookDTO getBookById(Long bookId, boolean authorData)
	{
		Book book;
		List<BookAuthor> bookAuthors = null;
		book= bookRepository.findById(bookId).orElse(null);
		if(authorData==true)
		{
			bookAuthors = bookAuthorRepo.findAllByBookId(bookId);
		}
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setName(book.getName());
		bookDTO.setDesc(book.getDesc());
		bookDTO.setYearOfPublication(book.getYearOfPublication());
		bookDTO.setBookType(book.getBookType());

		List<AuthorDTO> authorDTOList = new ArrayList<>();
		if(bookAuthors!=null)
		{
			for(BookAuthor bookAuthor :bookAuthors)
			{
				Author author = bookAuthor.getAuthor();

				AuthorDTO authorDTO = new AuthorDTO();
				authorDTO.setId(author.getId());
				authorDTO.setName(author.getName());
				authorDTO.setGender(author.getGender());
				authorDTOList.add(authorDTO);
			}
			bookDTO.setAuthors(authorDTOList);
		}
		return bookDTO;
	}



	public Book updateBook(Book incommingBook) {

		return bookRepository.save(incommingBook);
	}


	public String deleteBookById(Long id) {

		bookRepository.deleteById(id);
		return "The Book deleted successfully";
	}


	public APIResponse getBookByRawQuery(Set<Integer> yop) {
		APIResponse apiResponse = new APIResponse();
		List<Book> booklist = bookRepository.findAllByYearOfPublicationIns(yop);

		BookData bookData = new BookData();
		bookData.setBooks(booklist);
		apiResponse.setData(bookData);
		return apiResponse;
	}


	public APIResponse getCoughtException(Integer yop) {
		int result = 100/yop;
		APIResponse response = new APIResponse();
		response.setData(result);
		return response;
	}


	

}
