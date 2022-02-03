package brain.security.Dao;
import org.springframework.data.jpa.repository.JpaRepository;

import brain.security.Model.UserSecurity;


import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSecurity, Integer> {
    Optional<UserSecurity> findByUserName(String userName);
}
