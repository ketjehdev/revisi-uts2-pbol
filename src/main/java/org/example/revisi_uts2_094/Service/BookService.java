//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Service;

import jakarta.annotation.PostConstruct;
import org.example.revisi_uts2_094.Model.Book;
import org.example.revisi_uts2_094.Repository.BookRepository;
import org.hibernate.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    @PostConstruct
    public void initData() {
        bookRepository.save(new Book("Hujan", "Tere Liye", 2018, true));
        bookRepository.save(new Book("Matahari", "Tere Liye", 2012, true));
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new PropertyNotFoundException("No books found");
        }
        return books;
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setAvailability(updatedBook.getAvailability());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}