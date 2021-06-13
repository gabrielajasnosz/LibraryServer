package com.demo.springboot.service;

import com.demo.springboot.model.*;
import com.demo.springboot.repository.*;
import exception.BookNotFoundException;
import exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service containing business logic for the library.
 */
@Service
public class LibraryService {
    private final BookRepository bookRepo;
    private final ClientRepository clientRepo;
    private final PublishingHouseRepository publHouseRepo;
    private final AuthorRepository authorRepo;
    private final RentalRepository rentalRepo;
    private final AdminRepository adminRepo;
    private final CategoryRepository catRepo;
    private final RentalHistoryRepository rentalHistoryRepo;

    /**
     * All the necessary service's dependency injections.
     */
    @Autowired
    public LibraryService(BookRepository bookRepo, ClientRepository clientRepo, PublishingHouseRepository publHouseRepo, AuthorRepository authorRepo, RentalRepository rentalRepo, AdminRepository adminRepo, CategoryRepository catRepo, RentalHistoryRepository rentalHistoryRepo) {
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
        this.publHouseRepo = publHouseRepo;
        this.authorRepo = authorRepo;
        this.rentalRepo = rentalRepo;
        this.adminRepo = adminRepo;
        this.catRepo = catRepo;
        this.rentalHistoryRepo = rentalHistoryRepo;
    }

    //BOOK SECTION_______________________________________

    /**
     * Gets the book by its ID.
     *
     * @param bookId The books id number.
     * @return A book entity representing the returned book. If no book is returned, method throws an BookNotFoundException.
     */
    public Book findBookByBookId(Long bookId) {
        return bookRepo.findBookByBookId(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));
    }

    /**
     * Gets list of all books.
     *
     * @return A List of books representing all the books in database.
     */
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    /**
     * Gets the books by their category
     *
     * @param id The category's id number.
     * @return A List of books representing all the books in certain category
     */
    public List<Book> getBooksByCategory(Long id) {
        return bookRepo.getBooksByCategory(id);
    }

    /**
     * Adds a new book to the database via JPA repo.
     *
     * @param book The book to add.
     * @return An added book.
     */
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    /**
     * Updates an existing book in the database via JPA repo.
     *
     * @param book The book to update.
     * @return An updated book.
     */
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    /**
     * Deletes an existing book by its id in the database via JPA repo.
     *
     * @param bookId The book's id to delete.
     * @return A deleted book. If no book with specified ID is found, method returns an exception.
     */
    public Book deleteBookByBookId(Long bookId) {
        if (bookRepo.existsById(bookId)) {
            Book removedBook = bookRepo.deleteBookByBookId(bookId).get(0);
            return removedBook;
        } else {
            throw new BookNotFoundException("Book with id " + bookId + " not found");
        }
    }

    /**
     * Gets a list of all the categories.
     *
     * @return A list of categories. Returns an empty list if there are no categories.
     */
    //END OF BOOK SECTION_______________________________________
    public List<Category> getAllCategories() {
        return catRepo.findAll();
    }

    /**
     * Adds a new client to the database via JPA repo.
     *
     * @param client The client to add.
     * @return An added client.
     */
    //CLIENT SECTION_______________________________________
    public Client addClient(Client client) {
        return clientRepo.save(client);
    }


    /**
     * Checks if client with specified login exists in the database.
     *
     * @param login The client's login to lookup.
     * @return True if client is found, false if not.
     */
    public boolean existsByLogin(String login) {
        return clientRepo.existsByLogin(login);
    }

    /**
     * Updates an existing client in the database via JPA repo.
     *
     * @param client The client to update.
     * @return An updated client.
     */
    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    /**
     * Gets a client by their login and password.
     *
     * @return A client entity is returned.
     */
    public Client getClient(Client client) {
        return clientRepo.findClientByLoginAndPassword(client.getLogin(), client.getPassword());
    }

    /**
     * Checks if client with specified login and password exists in the database.
     *
     * @param login    The client's login to check.
     * @param password The client's password to check.
     * @return True if client is found, false if not.
     */
    public boolean existsByLoginAndPassword(String login, String password) {
        return clientRepo.existsByLoginAndPassword(login, password);
    }

    /**
     * Checks if client with specified login and password exists in the database.
     *
     * @param login    The client's login to check.
     * @param password The client's password to check.
     * @return Returns a client entity if a client with specified login and password exists in the db.
     */
    public Client findClientByLoginAndPassword(String login, String password) {
        return clientRepo.findClientByLoginAndPassword(login, password);
    }

    /**
     * Checks if client with specified login and password exists in the database.
     * If the client exists, the method checks if the client has got any rentals.
     * If no rentals are found, the client data is anonymized (put into rental history with anonymized client data)
     * Lastly, the  client is removed from the database.
     *
     * @param login    The client's login to check.
     * @param password The client's password to check.
     * @return Returns a deleted client if deletion was successful, or else throws an exception.
     */
    public Client deleteClientByLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            if (rentalRepo.getRentalByClientLoginAndPassword(login, password).isEmpty()) {
                Client removedClient = clientRepo.findClientByLoginAndPassword(login, password);

                clientRepo.anonymizeClientByLoginAndPassword(login, password);
                return removedClient;
            } else {
                throw new IllegalStateException("Client has rentals.");
            }
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }
    //END OF CLIENT SECTION_______________________________________

    //PUBLISHING HOUSE______________________________________

    /**
     * Gets a list of all the publishing houses.
     *
     * @return A list of publishing houses Returns an empty list if there are no publishing houses.
     */
    public List<PublishingHouse> getAllPublishingHouses() {
        return publHouseRepo.findAll();
    }


    /**
     * Adds a new publishing house to the database via JPA repo.
     *
     * @param publishingHouse The publishing house to add.
     * @return An added publishing house .
     */
    public PublishingHouse addPublishingHouse(PublishingHouse publishingHouse) {
        if (publishingHouse.getCity() != null && publishingHouse.getName() != null) {
            return publHouseRepo.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }

    }

    //AUTHOR______________________________________

    /**
     * Gets a list of all the authors.
     *
     * @return A list of authors. Returns an empty list if there are no authors.
     */
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    /**
     * Adds a new Author to the database via JPA repo.
     *
     * @param author The Author to add.
     * @return An added Author .
     */
    public Author addAuthor(Author author) {
        if (author.getName() != null && author.getNationality() != null && author.getSurname() != null) {
            return authorRepo.save(author);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    //Rentals

    /**
     * A method for admin. It gets a list of all the client's rentals.
     *
     * @return A list of rentals. Returns an empty list if there are no rentals.
     */
    public List<Rental> getAllRentals() {
        return rentalRepo.findAll();
    }

    /**
     * A client's method. It gets a list of all his rentals.
     *
     * @param login    The client's login.
     * @param password The client's password
     * @return A list of rentals. Returns an empty list if there are no rentals. An error is thrown if the login credentials are wrong.
     */
    public List<Rental> getRentalByClientLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            return rentalRepo.getRentalByClientLoginAndPassword(login, password);
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }

    /**
     * A client's method. For renting a book.
     * First, the method checks if a book with specified id exists in the database
     * Next, it checks the availability of the book
     * Then, it checks if client with given login and password exists
     * Lastly, it adds a new rental to the database and checks availability of rented book to false.
     *
     * @param login    The client's login.
     * @param password The client's password
     * @param bookId   The book's id
     * @return If operation is successful, a rental entity is returned. Method throws many exceptions, depending on an error.
     */
    public Rental rentBook(String login, String password, Long bookId) {
        if (bookRepo.existsById(bookId)) {
            if (bookRepo.checkAvailabilityByBookid(bookId)) {
                if (clientRepo.existsByLoginAndPassword(login, password)) {
                    Rental newRental = rentalRepo.rentBook(login, password, bookId);
                    bookRepo.setFalse(bookId);
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

    /**
     * An admin's method used to delete existing client's rental.
     * First, the method checks if a rental with specified id exists in the database
     * Next, moves the rental to the rental history table
     * Then, it sets the availability of a book to true
     * Lastly, deletes the rental.
     *
     * @param rentalId The client's login.
     * @return If operation is successful, a rental entity is returned. Method throws many exceptions, depending on an error.
     */
    public Rental deleteActiveRentalByRentalId(Long rentalId) {
        if (rentalRepo.existsById(rentalId)) {
            Rental rentalToStore = rentalRepo.findRentalByRentalId(rentalId);
            RentalHistory newRentalHistory = new RentalHistory(rentalToStore.getRentalDate(), rentalToStore.getReturnDate(), rentalToStore.getBook(), rentalToStore.getClient());

            rentalHistoryRepo.save(newRentalHistory);
            Rental removedRental = rentalRepo.deleteRentalByRentalId(rentalId).get(0);

            bookRepo.setTrue(removedRental.getBook().getBookId());
            return removedRental;
        } else {
            throw new BookNotFoundException("Rental with id " + rentalId + " not found");
        }
    }

    //ADMIN

    /**
     * Checks if admin with specified login and password exists in the database.
     *
     * @param login    The client's login to check.
     * @param password The client's password to check.
     * @return Returns an admin entity if admin with specified login and password exists in the db.
     */
    public Boolean adminExistsByLoginAndPassword(String login, String password) {
        if (adminRepo.existsByLoginAndPassword(login, password) == true) {
            return true;
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }

    /**
     * Gets admin data for given login and password.
     *
     * @return Returns an admin entity if admin with specified login and password exists in the db.
     */
    public Admin getAdmin(Admin admin) {
        return adminRepo.findAdminByLoginAndPassword(admin.getLogin(), admin.getPassword());
    }
}
