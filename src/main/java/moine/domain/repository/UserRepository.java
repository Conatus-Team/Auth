package moine.domain.repository;

import moine.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(Long userId);
    boolean existsByEmail(String email);
    List<User> findByEmailAndPassword(String email, String password);


}
