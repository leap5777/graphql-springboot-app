package com.logwin.graphql.service;

import com.logwin.graphql.dto.request.BookRequest;
import com.logwin.graphql.exception.BookNotFoundException;
import com.logwin.graphql.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public Book findById(String id) throws BookNotFoundException;
    public Book saveBook(Book book);
    public Book updateBook(String id, BookRequest bookRequest)  throws  BookNotFoundException ;
    public void deleteBook(String id) throws  BookNotFoundException;
}

