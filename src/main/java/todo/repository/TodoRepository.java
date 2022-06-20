package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.model.Todo;

import java.util.List;


public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Todo findByContent(String content);
    List<Todo> findByDeletedFalse();

}
