package todo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import todo.model.Todo;

import java.util.List;


public interface TodoRepository extends CrudRepository<Todo, Integer> {
    Todo findByContent(String content);
    List<Todo> findByDeletedFalse();
    @Query("select t from Todo t where user_id = :id and t.deleted = false")
    List<Todo> findByUserIdAndDeletedFalse(@Param("id") Integer id);
}
