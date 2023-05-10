package com.siucng.booklibrary;

import static org.junit.Assert.assertEquals;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    private Book newBook;
    private Book storedBook;
    

    private RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8082/books";
    
    @Given("Book {string} with ISBN number {string}")
    public void book_by_with_isbn_number(String name, String isbn) {
        this.newBook = new Book(name, isbn);
    }

    @When("I store the book in library")
    public void i_store_the_book_in_library() {
        this.storedBook = restTemplate.postForObject(url, newBook, Book.class);
    }

    @Then("I am able to retrieve the book by the ISBN number")
    public void getBookByIsbn() {
        Book returnedbook = restTemplate.getForObject(url + "/" + this.newBook.getIsbn(), Book.class);
        assertEquals(newBook, returnedbook);
        assertEquals(newBook, this.storedBook);
    }
}
