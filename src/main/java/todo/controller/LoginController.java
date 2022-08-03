package todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.ErrorResponse;
import todo.model.User;
import todo.repository.UserRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public @ResponseBody ResponseEntity<?> login(@RequestBody User userToLog){
        User user = userRepository.findByNameAndPassword(userToLog.getName(), userToLog.getPassword());

        if(user == null){
            ErrorResponse response = new ErrorResponse();
            return new ResponseEntity<ErrorResponse>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
}
