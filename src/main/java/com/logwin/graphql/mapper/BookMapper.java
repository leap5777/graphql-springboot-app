package com.logwin.graphql.mapper;

import com.logwin.graphql.dto.request.BookRequest;
import com.logwin.graphql.model.Book;

import java.util.UUID;

public class BookMapper {

    private BookMapper(){
    }

    public static Book fromRequestToBook(BookRequest bookRequest){
        return new Book(UUID.randomUUID().toString(),bookRequest.getTitle(),bookRequest.getAuthor(),bookRequest.getPrice());
    }

}
