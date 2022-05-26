package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.model.Type;

public interface TypeRepository extends CrudRepository<Type, Integer> {
}
