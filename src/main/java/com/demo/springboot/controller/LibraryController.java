package com.demo.springboot.controller;

import com.demo.springboot.model.*;
import com.demo.springboot.service.LibraryService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/library")

public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    //BOOK SECTION_______________________________________
    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllUsers() {
        List<Book> books = libraryService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
        Book book = libraryService.findBookByBookId(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(
            value = "/image/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )

    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable Integer id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/imgs/" + id + ".jpg");
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = libraryService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/update")
    public ResponseEntity<Book> updateClient(@RequestBody Book book) {
        Book updatedBook = libraryService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/books/delete_book")
    public ResponseEntity<Book> deleteBook(@RequestParam(value = "book_id", required = true) Long bookId) {
        Book removedBook = libraryService.deleteBookByBookId(bookId);
        return new ResponseEntity<>(removedBook, HttpStatus.OK);
    }

    //END OF BOOK SECTION_______________________________________

    //CLIENT SECTION_______________________________________
    @GetMapping("/client")
    public ResponseEntity<Client> getClientData(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        Client client = libraryService.findClientByLoginAndPassword(login, password);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/client/register")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = libraryService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/client/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client updatedClient = libraryService.updateClient(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @PostMapping(value = "/client/login")
    public ResponseEntity<Client> login(@RequestBody Client client) {
        Client loggedClient = libraryService.getClient(client);
        if (!libraryService.existsByLoginAndPassword(client.getLogin(),client.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(loggedClient,HttpStatus.OK);
        }
    }

    @Transactional
    @DeleteMapping("/client/delete_account")
    public ResponseEntity<Client> deleteClient(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        Client removedClient = libraryService.deleteClientByLoginAndPassword(login, password);
        return new ResponseEntity<>(removedClient, HttpStatus.OK);
    }
    //End of CLIENT__________________________________________

    //PUBLISHING HOUSE______________________________________
    @GetMapping("/publishing_houses/all")
    public ResponseEntity<List<PublishingHouse>> getAllPublishingHouses() {
        List<PublishingHouse> publishingHouses = libraryService.getAllPublishingHouses();
        return new ResponseEntity<>(publishingHouses, HttpStatus.OK);
    }


    @PostMapping("/publishing_houses/add")
    public ResponseEntity<PublishingHouse> addPublishingHouse(@RequestBody PublishingHouse publishingHouse) {
        PublishingHouse newPublishingHouse = libraryService.addPublishingHouse(publishingHouse);
        return new ResponseEntity<>(newPublishingHouse, HttpStatus.CREATED);
    }
    //END OF PUBLISHING HOUSE SECTION_______________________________________


    //AUTHOR______________________________________
    @GetMapping("/authors/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = libraryService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping("/authors/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author newAuthor = libraryService.addAuthor(author);
        return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }

    //RENTED BOOKS SECTION_______________________________________
    @GetMapping("/books/rented/all")
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = libraryService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/books/rented/get_client_rentals")
    public ResponseEntity<List<Rental>> getClientRentals(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        List<Rental> clientRentals = libraryService.getRentalByClientLoginAndPassword(login, password);
        return new ResponseEntity<>(clientRentals, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/books/rented/delete_rent")
    public ResponseEntity<Rental> deleteRent(@RequestParam(value = "rentalId", required = true) Long rentalId) {
        Rental removedRent = libraryService.deleteRentalByBookId(rentalId);
        return new ResponseEntity<>(removedRent, HttpStatus.OK);
    }

    @PostMapping("/books/rent")
    public ResponseEntity<Rental> rentBook(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password, @RequestParam(value = "book_id", required = true) Long bookId)  {
        Rental newRental = libraryService.rentBook(login,password,bookId);
        return new ResponseEntity<>(newRental, HttpStatus.OK);
    }


    //ADMIN
    @PostMapping(value = "/admin/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) {
        Admin logAdmin = libraryService.getAdmin(admin);
        if (libraryService.adminExistsByLoginAndPassword(admin.getLogin(), admin.getPassword()) == false) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(logAdmin,HttpStatus.OK);
        }
    }
}
