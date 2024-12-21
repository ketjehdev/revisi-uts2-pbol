//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Service;

import org.example.revisi_uts2_094.Model.Borrowing;
import org.example.revisi_uts2_094.Model.Return;
import org.example.revisi_uts2_094.Repository.BorrowingRepository;
import org.example.revisi_uts2_094.Repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReturnService {
    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    public Return processReturn(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing record not found"));

        if (borrowing.getReturnDate() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book has already been returned");
        }

        // Tandai pengembalian di Borrowing
        borrowing.setReturnDate(LocalDateTime.now());
        borrowingRepository.save(borrowing);

        // Buat catatan pengembalian
        Return bookReturn = new Return();
        bookReturn.setBorrowing(borrowing);
        bookReturn.setReturnDate(LocalDateTime.now());
        return returnRepository.save(bookReturn);
    }

    // Admin melihat semua transaksi pengembalian
    public List<Return> getAllReturns() {
        return returnRepository.findAll();
    }
}
