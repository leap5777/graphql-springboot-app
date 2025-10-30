package com.logwin.graphql.books;

import com.logwin.graphql.model.Book;
import com.logwin.graphql.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
/*
@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureGraphQlTester
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookGraphQlIntegrationTests {

    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    public int port;

    private String jwtToken;

    @BeforeAll
    void generateJwtToken(){
        jwtToken = webTestClient.post()
                .uri("/auth/token")
                .bodyValue(Map.of("username", "user1", "password", "pass1"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Map.class)
                .returnResult()
                .getResponseBody()
                .get("token")
                .toString();

        WebTestClient baseWebTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:"+port+"/graphql")
                .defaultHeader("Authorization","Bearer " + jwtToken)
                .build();

        graphQlTester = HttpGraphQlTester.create(baseWebTestClient);


        log.info("Jwt token: " + jwtToken);
    }

    @BeforeEach
    void setup(){
        bookRepository.clear();
    }

    @Test
    void testGetAllBooks() {
        graphQlTester.document("{ getAllBooks { id title author price } }")
                .execute()
                .path("getAllBooks").entityList(Book.class)
                .hasSize(3) // your static data has 3 books
                .satisfies(books -> {
                    books.forEach(book -> {
                        assertThat(book.getId()).isNotBlank();
                    });
                });
    }

    @Test
    void testAddBook() {
        String mutation = """
            mutation {
                addBook(bookRequest: {title: "New Book", author: "Author X", price: 25.5}) {
                    id
                    title
                    author
                    price
                }
            }
        """;

        graphQlTester.document(mutation)
                .execute()
                .path("addBook.id").hasValue()
                .path("addBook.title").entity(String.class).isEqualTo("New Book")
                .path("addBook.author").entity(String.class).isEqualTo("Author X")
                .path("addBook.price").entity(Double.class).isEqualTo(25.5);
    }

    @Test
    void testFindBookById(){
        String existingId = "752c488c-cd3b-4a74-a14b-84c6583921ba";

        String mutation = """
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
                .document(mutation)
                .variable("id",existingId)
                .execute()
                .path("findBookById")
                .entity(Book.class)
                .satisfies(book -> {
                   assertThat(book.getId()).isEqualTo(existingId);
                   assertThat(book.getTitle()).isEqualTo("t1");
                   assertThat(book.getAuthor()).isEqualTo("shakesphere");
                   assertThat(book.getPrice()).isEqualTo(20.2);
                });
    }

    @Test
    void testUpdateBook() {
        String existingId = "752c488c-cd3b-4a74-a14b-84c6583921ba";

        String mutation = String.format("""
            mutation {
                updateBook(id: "%s", bookRequest: {price: 99.9}) {
                    id
                    title
                    author
                    price
                }
            }
        """, existingId);

        graphQlTester.document(mutation)
                .execute()
                .path("updateBook.id").entity(String.class).isEqualTo(existingId)
                .path("updateBook.price").entity(Double.class).isEqualTo(99.9);
    }

    @Test
    void testDeleteBook() {
        String existingId = "153587b6-cd79-40e7-bb43-807479fab273";

        String mutation = String.format("""
            mutation {
                deleteBook(id: "%s")
            }
        """, existingId);

        graphQlTester.document(mutation)
                .execute()
                .path("deleteBook").entity(String.class)
                .isEqualTo("Deleted Book Successfully ...");
    }

}
*/
