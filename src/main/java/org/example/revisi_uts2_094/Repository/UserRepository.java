//maria gresia plena br purba
//235314094

package org.example.revisi_uts2_094.Repository;

import org.example.revisi_uts2_094.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
