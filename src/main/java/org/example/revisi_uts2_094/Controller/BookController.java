// Maria Gresia Plena Br Purba
// 235314094
package org.example.revisi_uts2_094.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.revisi_uts2_094.Model.Book;
import org.example.revisi_uts2_094.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Get all books
//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks() {
//        try {
//            List<Book> books = bookService.getAllBooks();
//            return new ResponseEntity<>(books, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Get book by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
//        Optional<Book> book = bookService.findById(id);
//        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    // Add a new book
//    @PostMapping
//    public ResponseEntity<Book> addBook(@RequestBody Book book) {
//        try {
//            Book newBook = bookService.addBook(book);
//            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Update an existing book
//    @PutMapping("/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
//        try {
//            Book book = bookService.updateBook(id, updatedBook);
//            return new ResponseEntity<>(book, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete a book
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
//        try {
//            bookService.deleteBook(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        System.out.println("Role from request: " + role);
        if ("USER".equals(role) || "ADMIN".equals(role)) {
            System.out.println("Access granted for role: " + role);
            return ResponseEntity.ok(bookService.getAllBooks());
        }
        System.out.println("Access denied for role: " + role);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    @PostMapping
    public ResponseEntity<?> addBook(HttpServletRequest request, @RequestBody Book product) {
        String role = (String) request.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            System.out.println("Access denied for role: " + role);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Only ADMIN can add products.");
        }
        System.out.println("Access granted for ADMIN to add product.");
        return ResponseEntity.ok(bookService.addBook(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(HttpServletRequest request, @PathVariable Long id) {
        String role = (String) request.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            System.out.println("Access denied for role: " + role);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Only ADMIN can delete products.");
        }
        bookService.deleteBook(id);
        System.out.println("Book deleted with ID: " + id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
