package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.Type;
import todo.repository.TypeRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping
    public @ResponseBody Iterable<Type> getAllTodos() {
        // This returns a JSON or XML with the user
        return typeRepository.findAll();
    }
}
