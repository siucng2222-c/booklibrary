package com.siucng.booklibrary;

import java.util.Hashtable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    private Hashtable<String, Book> books;

    public BooksController() {
        super();
        books = new Hashtable<String, Book>();
        books.put("isbn1", new Book("name1", "isbn1"));
        books.put("isbn2", new Book("name2", "isbn2"));
    }

    @GetMapping("books")
    public ResponseEntity<Hashtable<String, Book>> books() {
        return new ResponseEntity<Hashtable<String, Book>>(books, HttpStatus.OK);
    }

    @GetMapping("books/{isbn}")
    public ResponseEntity<Book> books(@PathVariable("isbn") String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(books.get(isbn), HttpStatus.OK);
    }

    @PostMapping(path = "books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@RequestBody Book newBook) {

        if (newBook == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            books.put(newBook.getIsbn(), newBook);
            return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
        }
    }

}
