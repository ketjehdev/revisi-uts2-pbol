//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Controller;

import org.example.revisi_uts2_094.Model.Borrowing;
import org.example.revisi_uts2_094.Service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    // User meminjam buku
    @PostMapping
    public ResponseEntity<Borrowing> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowingService.createBorrowing(userId, bookId));
    }

    // Admin melihat semua transaksi peminjaman
    @GetMapping
    public ResponseEntity<List<Borrowing>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllBorrowings());
    }

    // User melihat daftar buku yang sedang dipinjam
    @GetMapping("/my")
    public ResponseEntity<List<Borrowing>> getMyBorrowings(Principal principal) {
        String username = principal.getName();
        Long userId = borrowingService.getUserIdByUsername(username);
        return ResponseEntity.ok(borrowingService.getBorrowingByUser(userId));
    }
}

