package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByNameAndPassword(String name, String password);
}
