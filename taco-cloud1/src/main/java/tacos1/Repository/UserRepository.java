package tacos1.Repository;

import org.springframework.data.repository.CrudRepository;
import tacos1.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
