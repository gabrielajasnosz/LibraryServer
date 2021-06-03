package com.demo.springboot.service;

import com.demo.springboot.model.*;
import com.demo.springboot.repository.*;
import exception.BookNotFoundException;
import exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final BookRepository bookRepo;
    private final ClientRepository clientRepo;
    private final PublishingHouseRepository publHouseRepo;
    private final AuthorRepository authorRepo;
    private final RentalRepository rentalRepo;
    private final AdminRepository adminRepo;

    @Autowired
    public LibraryService(BookRepository bookRepo, ClientRepository clientRepo, PublishingHouseRepository publHouseRepo, AuthorRepository authorRepo, RentalRepository rentalRepo, AdminRepository adminRepo) {
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
        this.publHouseRepo = publHouseRepo;
        this.authorRepo = authorRepo;
        this.rentalRepo = rentalRepo;
        this.adminRepo = adminRepo;
    }

    //BOOK SECTION_______________________________________
    public Book findBookByBookId(Long bookId) {
        return bookRepo.findBookByBookId(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    public Book deleteBookByBookId(Long bookId) {
        if (bookRepo.existsById(bookId)) {
            Book removedBook = bookRepo.deleteBookByBookId(bookId).get(0);
            return removedBook;
        } else {
            throw new BookNotFoundException("Book with id " + bookId + " not found");
        }
    }

    //END OF BOOK SECTION_______________________________________


    //CLIENT SECTION_______________________________________
    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    public Client getClient(Client client)
    {
        return clientRepo.findClientByLoginAndPassword(client.getLogin(),client.getPassword());
    }


    public boolean existsByLoginAndPassword(String login, String password) {
        return clientRepo.existsByLoginAndPassword(login, password);
    }

    public Client findClientByLoginAndPassword(String login, String password) {
        return clientRepo.findClientByLoginAndPassword(login, password);
    }

    public Client deleteClientByLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            Client removedClient = clientRepo.deleteClientByLoginAndPassword(login, password).get(0);
            return removedClient;
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }
    //END OF CLIENT SECTION_______________________________________

    //PUBLISHING HOUSE______________________________________
    public List<PublishingHouse> getAllPublishingHouses() {
        return publHouseRepo.findAll();
    }

    public PublishingHouse addPublishingHouse(PublishingHouse publishingHouse) {
        if (publishingHouse.getCity() != null && publishingHouse.getName() != null) {
            return publHouseRepo.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }

    }

    //AUTHOR______________________________________
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author addAuthor(Author author) {
        if (author.getName() != null && author.getNationality() != null && author.getSurname() != null) {
            return authorRepo.save(author);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }

    }

    //Rentals
    public List<Rental> getAllRentals() {
        return rentalRepo.findAll();
    }

    public List<Rental> getRentalByClientLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            return rentalRepo.getRentalByClientLoginAndPassword(login, password);
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }

    public Rental rentBook(String login, String password, Long bookId) {
        if (bookRepo.existsById(bookId)) {
            if (bookRepo.checkAvailabilityByBookid(bookId)) {
                System.out.println("is available");
                if (clientRepo.existsByLoginAndPassword(login, password)) {
                    Rental newRental = rentalRepo.rentBook(login, password, bookId);
                    bookRepo.changeAvailability(bookId);
                    return newRental;
                } else {
                    throw new ClientNotFoundException("Wrong login or password.");
                }
            } else {
                throw new RuntimeException("Book with id " + bookId + " is unavailable");
            }
        } else {
            throw new BookNotFoundException("Book with id " + bookId + " not found");
        }
    }
    public Rental deleteRentalByBookId(Long rentalId) {
        if (rentalRepo.existsById(rentalId)) {
            Rental removedRental = rentalRepo.deleteRentalByRentalId(rentalId).get(0);
            return removedRental;
        } else {
            throw new BookNotFoundException("Rental with id " + rentalId + " not found");
        }
    }

    //ADMIN
    public Boolean adminExistsByLoginAndPassword(String login, String password) {
        if (adminRepo.existsByLoginAndPassword(login, password) == true) {
            return true;
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }

    public Admin getAdmin(Admin admin)
    {
        return adminRepo.findAdminByLoginAndPassword(admin.getLogin(),admin.getPassword());
    }

}
