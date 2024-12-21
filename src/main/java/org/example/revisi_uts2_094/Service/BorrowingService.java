//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Service;

import org.example.revisi_uts2_094.Model.Book;
import org.example.revisi_uts2_094.Model.Borrowing;
import org.example.revisi_uts2_094.Model.User;
import org.example.revisi_uts2_094.Repository.BookRepository;
import org.example.revisi_uts2_094.Repository.BorrowingRepository;
import org.example.revisi_uts2_094.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Borrowing createBorrowing(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if (!book.getAvailability()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is not available for borrowing");
        }

        book.setAvailability(false);
        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDateTime.now());
        return borrowingRepository.save(borrowing);
    }

    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public List<Borrowing> getBorrowingByUser(Long userId) {
        return borrowingRepository.findByUserId(userId);
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return user.getId();
    }
}
