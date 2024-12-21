//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Repository;

import org.example.revisi_uts2_094.Model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserId(Long userId);
}
