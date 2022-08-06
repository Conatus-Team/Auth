package moine.domain.repository;

import moine.domain.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
    boolean existsByUserId(Long userId);

}
