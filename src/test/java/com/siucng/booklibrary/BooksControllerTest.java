package com.siucng.booklibrary;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Hashtable;

// import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BooksControllerTest {
    
    @InjectMocks
    private BooksController booksController = new BooksController();

    private Hashtable<String, Book> books;

    @BeforeEach
    public void init() {
        books = new Hashtable<String, Book>();
        books.put("isbn2", new Book("name2", "isbn2"));
        books.put("isbn1", new Book("name1", "isbn1"));
    }

    @Test
    public void testGetAllBooks(){

        // Hashtable<String, Book> books = new Hashtable<String, Book>();
        // books.put("isbn2", new Book("name2", "isbn2"));
        // books.put("isbn1", new Book("name1", "isbn1"));
        

        ResponseEntity<Hashtable<String, Book>> responseEntity =  booksController.books();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        // assertThat(responseEntity.getBody().get("isbn1")).isEqualTo(books.get("isbn1"));
        assertThat(responseEntity.getBody()).isEqualTo(books);
    }

    @Test
    public void testGetBookByIsbn(){

        // Hashtable<String, Book> books = new Hashtable<String, Book>();
        // books.put("isbn2", new Book("name2", "isbn2"));
        // books.put("isbn1", new Book("name1", "isbn1"));
        

        ResponseEntity<Book> responseEntity =  booksController.books("isbn2");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(books.get("isbn2"));
        
    }

    @Test
    public void testAddBook(){

        Book newBook = new Book("name3", "isbn3");

        ResponseEntity<Book> responseEntity =  booksController.create(newBook);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(newBook);

        ResponseEntity<Hashtable<String, Book>> responseEntity2 =  booksController.books();

        assertThat(responseEntity2.getStatusCodeValue()).isEqualTo(200);
        // assertThat(responseEntity.getBody().get("isbn1")).isEqualTo(books.get("isbn1"));
        assertThat(responseEntity2.getBody().get(newBook.getIsbn())).isEqualTo(newBook);
        
    }
}

