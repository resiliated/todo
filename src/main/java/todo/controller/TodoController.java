package todo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.Todo;
import todo.repository.TodoRepository;
import org.slf4j.Logger;


@CrossOrigin("*")
@RestController
@RequestMapping("todo")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @DeleteMapping(path="/{id}")
    public @ResponseBody Todo removeTodo(@PathVariable Integer id){
        todoRepository.findById(id).map(todo -> {
            todo.setDeleted(true);
            return todoRepository.save(todo);
        });
        return new Todo();
    }
    @PutMapping
    public @ResponseBody Todo updateTodo(@RequestBody Todo todoToCreateOrUpdate){
        if(todoToCreateOrUpdate.getId() == null){
            Todo newTodo = new Todo();

            newTodo.setTitle(todoToCreateOrUpdate.getTitle());
            newTodo.setContent(todoToCreateOrUpdate.getContent());
            return todoRepository.save(newTodo);
        }
        return todoRepository.findById(todoToCreateOrUpdate.getId()).map(todo ->{
            if(!todo.getCreation().equals(todoToCreateOrUpdate.getCreation())){
                logger.warn("Inconsistant create date:" + todo.getCreation() + "VS"+ todoToCreateOrUpdate.getCreation());
            }
            todo.setContent(todoToCreateOrUpdate.getContent());
            todo.setTitle(todoToCreateOrUpdate.getTitle());
            todo.setClose(todoToCreateOrUpdate.getClose());
            todo.setState(todoToCreateOrUpdate.getState());
            todo.setDeleted(todoToCreateOrUpdate.isDeleted());
            return todoRepository.save(todo);
        }).orElseGet(() -> {
            return todoRepository.save(todoToCreateOrUpdate);
        });
    }
    @PostMapping
    public @ResponseBody Todo createTodo(@RequestBody Todo todoToCreate) {
        Todo newTodo = new Todo();
        newTodo.setTitle(todoToCreate.getTitle());
        newTodo.setContent(todoToCreate.getContent());
        newTodo.setUserId(todoToCreate.getUserId());
        return todoRepository.save(newTodo);
    }
    @GetMapping(path="/{id}")
    public @ResponseBody Todo getTodo(@PathVariable Integer id){
        return todoRepository.findById(id).orElseThrow(); //TODO make dedicated exception
    }
    @GetMapping
    public @ResponseBody Iterable<Todo> getAllTodos() {
        return todoRepository.findByDeletedFalse();
    }
    @GetMapping(path="user/{userId}")
    public @ResponseBody Iterable<Todo> getTodosByUserId(@PathVariable Integer userId) {
        return todoRepository.findByUserIdAndDeletedFalse(userId);
    }
}