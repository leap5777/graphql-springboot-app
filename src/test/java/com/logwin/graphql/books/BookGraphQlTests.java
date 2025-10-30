package com.logwin.graphql.books;

import com.logwin.graphql.controller.BookController;
import com.logwin.graphql.model.Book;
import com.logwin.graphql.repository.BookRepository;
import com.logwin.graphql.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;


import static org.assertj.core.api.Assertions.assertThat;

@Import({BookRepository.class, BookServiceImpl.class})
@GraphQlTest(BookController.class)
public class BookGraphQlTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setup(){
        bookRepository.clear();
    }

    @Test
    void testAddBook(){

        String mutation = """
                mutation{
                    addBook(bookRequest: {
                        title: "Clean Code",
                        author: "Robert C. Martin",
                        price: 29.9
                    }) {
                        id
                        title
                        author
                        price
                    }
                }
                """;

        var response = graphQlTester
                .document(mutation)
                .execute()
                .path("addBook").entity(Book.class)
                .get();

        assertThat(response.getId()).isNotBlank();
        assertThat(response.getTitle()).isEqualTo("Clean Code");
        assertThat(response.getAuthor()).isEqualTo("Robert C. Martin");
        assertThat(response.getPrice()).isEqualTo(29.9);
    }

    @Test
    void testGetAllBooks(){

        String query = """
                {
                    getAllBooks{
                        id
                        title
                        author
                        price
                    }
                }
                """;

        graphQlTester
                .document(query)
                .execute()
                .path("getAllBooks")
                .entityList(Book.class)
                .satisfies(books -> {
                   assertThat(books).hasSize(3);
                   assertThat(books).allSatisfy(book -> assertThat(book.getId()).isNotBlank());
                });
    }

    @Test
    void testFindBookById(){
        String id = "153587b6-cd79-40e7-bb43-807479fab273";
        String query = """
                query ($id: String!){
                    findBookById(id: $id){
                        id
                        title
                        author
                        price
                    }
                }
                """;

        graphQlTester
                .document(query)
                .variable("id",id)
                .execute()
                .path("findBookById")
                .entity(Book.class)
                .satisfies(book -> {
                    assertThat(book.getId()).isEqualTo(id);
                    assertThat(book.getTitle()).isEqualTo("t2");
                    assertThat(book.getAuthor()).isEqualTo("jaimin");
                    assertThat(book.getPrice()).isEqualTo(50.2);
                });
    }

    @Test
    void testUpdatedBook_ChangesTitle(){

        String id = "153587b6-cd79-40e7-bb43-807479fab273";

        String mutation = """
                mutation{
                    updateBook(id: "%s", bookRequest: {
                        title: "Updated Title"
                    }){
                        id
                        title
                        author
                        price
                    }
                }
                """.formatted(id);

        graphQlTester
                .document(mutation)
                .execute()
                .path("updateBook")
                .entity(Book.class)
                .satisfies(book -> {
                    assertThat(book.getId()).isEqualTo(id);
                    assertThat(book.getTitle()).isEqualTo("Updated Title");
                });
    }

    @Test
    void testDeleteBook(){

        String id = "153587b6-cd79-40e7-bb43-807479fab273";

        String mutation = """
                mutation{
                    deleteBook(id: "%s")
                }
                """.formatted(id);

        graphQlTester
                .document(mutation)
                .execute()
                .path("deleteBook")
                .entity(String.class)
                .isEqualTo("Deleted Book Succesfully ...");
    }
}
