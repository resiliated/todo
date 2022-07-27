package todo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.Todo;
import todo.model.Type;
import todo.repository.TodoRepository;
import todo.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin("*")
@RestController
@RequestMapping("todo")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private TodoRepository todoRepository;

    @DeleteMapping(path="/{id}")
    public @ResponseBody String removeTodo(@PathVariable Integer id){
        todoRepository.findById(id).map(todo -> {
            todo.setDeleted(true);
            return todoRepository.save(todo);
        });
        return "Todo deleted";
    }

    @PutMapping
    public @ResponseBody Todo updateTodo(@RequestBody Todo todoToCreateOrUpdate){
        if(todoToCreateOrUpdate.getId() == null){
            Todo newTodo = new Todo();

            newTodo.setTitle(todoToCreateOrUpdate.getTitle());
            //newTodo.getType().setId(todoToCreateOrUpdate.getType().getId());
            newTodo.setContent(todoToCreateOrUpdate.getContent());
            return todoRepository.save(newTodo);
        }
        return todoRepository.findById(todoToCreateOrUpdate.getId()).map(todo ->{
            //todo.setCreation(newtodo.getCreation());

            if(!todo.getCreation().equals(todoToCreateOrUpdate.getCreation())){
                logger.warn("Inconsistant create date:" + todo.getCreation() + "VS"+ todoToCreateOrUpdate.getCreation());
            }
            todo.setContent(todoToCreateOrUpdate.getContent());
            todo.setTitle(todoToCreateOrUpdate.getTitle());
            todo.setClose(todoToCreateOrUpdate.getClose());
            todo.setState(todoToCreateOrUpdate.getState());
            //todo.setType(todoToCreateOrUpdate.getType());
            //logger.info("todo type :" + todo.getType().getId());
            return todoRepository.save(todo);
        }).orElseGet(() -> {
            return todoRepository.save(todoToCreateOrUpdate);
        });
    }

    /*@PostMapping // Map ONLY POST Requests
    public @ResponseBody Todo addNewTodo (@RequestBody Todo newtodo) {
        if(newtodo.getType().getId() != null){ //Create with an existing type
            Type type = typeRepository.findById(newtodo.getType().getId()).orElseThrow();

            if(!newtodo.getType().getEntitled().equals(type.getEntitled())){
                type.setEntitled(newtodo.getType().getEntitled());
            }
            newtodo.setType(type);
        }
        return todoRepository.save(newtodo);
    }*/

    /*
    TODO: remove ? temp to create random todo
     */
    @PostMapping // Map ONLY POST Requests
    public @ResponseBody Todo createTodo (@RequestBody Todo todoToCreate) {
        Todo newTodo = new Todo();
        newTodo.setTitle(todoToCreate.getTitle());
        newTodo.setContent(todoToCreate.getContent());
        return todoRepository.save(newTodo);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Todo getTodo(@PathVariable Integer id){

        return todoRepository.findById(id).orElseThrow(); //TODO make dedicated exception
    }

    @GetMapping
    public @ResponseBody Iterable<Todo> getAllTodos() {
        // This returns a JSON or XML with the user
        //return todoRepository.findAll();
        return todoRepository.findByDeletedFalse();
    }
}