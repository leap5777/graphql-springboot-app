package com.logwin.graphql.repository;

import com.logwin.graphql.dto.request.BookRequest;
import com.logwin.graphql.exception.BookNotFoundException;
import com.logwin.graphql.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class BookRepository {

    private final static Map<String,Book> dataMap = new ConcurrentHashMap<>();

    static
    {
        dataMap.put("752c488c-cd3b-4a74-a14b-84c6583921ba",new Book("752c488c-cd3b-4a74-a14b-84c6583921ba","t1","shakesphere",20.2));
        dataMap.put("153587b6-cd79-40e7-bb43-807479fab273",new Book("153587b6-cd79-40e7-bb43-807479fab273","t2","jaimin",50.2));
        dataMap.put("834a0307-e34b-4ff5-8571-cb09b06d68f1",new Book("834a0307-e34b-4ff5-8571-cb09b06d68f1","t3","poojan",90.2));
    }

    public List<Book> findAll(){
        log.info("Number of books : " + dataMap.values().size());
        return dataMap.values().stream().toList();
    }

    public Book findById(String id) throws BookNotFoundException {
        log.info("Present ids: " + dataMap.keySet());
        if(!dataMap.containsKey(id)){
            throw new BookNotFoundException(" Book Id:" + id + " Not Found... ");
        }
        log.info("id is : " + id);
        return dataMap.get(id);
    }

    public Book saveBook(Book book){
        dataMap.put(book.getId(),book);
        return book;
    }

    public void deleteBook(String id) throws BookNotFoundException{
        if(!dataMap.containsKey(id)){
            throw new BookNotFoundException(" Book Id:" + id + " Not Found... ");
        }
        dataMap.remove(id);
    }

    public Book updateBook(String id, BookRequest bookRequest) throws  BookNotFoundException{
        if(!dataMap.containsKey(id)){
            throw new BookNotFoundException(" Book Id:" + id + " Not Found... ");
        }
        Book book = dataMap.get(id);

        if(bookRequest.getTitle()!=null) book.setTitle(bookRequest.getTitle());

        if(bookRequest.getAuthor()!=null) book.setAuthor(bookRequest.getAuthor());

        if(bookRequest.getPrice()!=null) book.setPrice(bookRequest.getPrice());

        return book;
    }

    public void clear(){
        dataMap.clear();
        dataMap.put("752c488c-cd3b-4a74-a14b-84c6583921ba",new Book("752c488c-cd3b-4a74-a14b-84c6583921ba","t1","shakesphere",20.2));
        dataMap.put("153587b6-cd79-40e7-bb43-807479fab273",new Book("153587b6-cd79-40e7-bb43-807479fab273","t2","jaimin",50.2));
        dataMap.put("834a0307-e34b-4ff5-8571-cb09b06d68f1",new Book("834a0307-e34b-4ff5-8571-cb09b06d68f1","t3","poojan",90.2));
    }
}

