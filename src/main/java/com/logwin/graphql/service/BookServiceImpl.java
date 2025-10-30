package com.logwin.graphql.service;

import com.logwin.graphql.dto.request.BookRequest;
import com.logwin.graphql.exception.BookNotFoundException;
import com.logwin.graphql.model.Book;
import com.logwin.graphql.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private  final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String id) throws BookNotFoundException {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    public Book updateBook(String id, BookRequest bookRequest) throws  BookNotFoundException {
        return bookRepository.updateBook(id, bookRequest);
    }

    @Override
    public void deleteBook(String id) throws BookNotFoundException {
        bookRepository.deleteBook(id);
    }
}
