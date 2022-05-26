package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.model.Todo;


public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Todo findByContent(String content);

}
