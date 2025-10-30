package com.logwin.graphql.controller;

import com.logwin.graphql.dto.request.BookRequest;
import com.logwin.graphql.exception.BookNotFoundException;
import com.logwin.graphql.mapper.BookMapper;
import com.logwin.graphql.model.Book;
import com.logwin.graphql.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @QueryMapping
    public Book findBookById(@Argument String id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @MutationMapping
    public Book addBook(@Argument BookRequest bookRequest){
        return bookService.saveBook(BookMapper.fromRequestToBook(bookRequest));
    }

    @MutationMapping
    public Book updateBook(@Argument String id,@Argument BookRequest bookRequest)  throws  BookNotFoundException {
        return bookService.updateBook(id,bookRequest);
    }

    @MutationMapping
    public String deleteBook(@Argument String id) throws BookNotFoundException {
        bookService.deleteBook(id);
        return  "Deleted Book Succesfully ...";
    }
}
