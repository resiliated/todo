package todo.todo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Todo findByContent(String content);

}
