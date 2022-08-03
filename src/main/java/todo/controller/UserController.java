package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.model.User;
import todo.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping(path="/{id}")
    public @ResponseBody String removeUser(@PathVariable Integer id){
        userRepository.findById(id).map(user -> {
            return userRepository.save(user);
        });
        return "User deleted";
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getUser(@PathVariable Integer id){
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public @ResponseBody User createUser(@RequestBody User userToCreate) {
        System.out.println(userToCreate);
        User newUser = new User();
        newUser.setName(userToCreate.getName());
        newUser.setPassword(userToCreate.getPassword());
        return userRepository.save(newUser);
    }

    @PutMapping
    public @ResponseBody User updateUser(@RequestBody User userToUpdate) {
        if (userToUpdate.getId() == null) {
            return null;
        }
        return userRepository.findById(userToUpdate.getId()).map(user -> {
            user.setName(userToUpdate.getName());
            user.setPassword(userToUpdate.getPassword());
            return userRepository.save(user);
        }).orElseGet(() -> {
            return userRepository.save(userToUpdate);
        });
    }
}
