package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.entity.Todo;


public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Todo findByContent(String content);

}
