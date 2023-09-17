package by.toukach.authserver.data;

import by.toukach.authserver.authorization.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
