package AuthApp.controller;

import AuthApp.service.AuthenticationService;
import AuthApp.service.User;
import AuthApp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;
   @Autowired
   private AuthenticationService authenticationService;
   private static Logger logger = LogManager.getLogger(UserController.class.getName());

    private UserController() {
    }

    @RequestMapping(value = "name", method = RequestMethod.PATCH)
    public ResponseEntity updateUserName(@RequestParam String email, @RequestParam String name,
                                               @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidName(name)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name!");
        }

        if (!authenticationService.isValidToken(email, token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must be logged in first!");
        }

        return ResponseEntity.ok(userService.updateUserName(email, name));
    }

    @RequestMapping(value = "email", method = RequestMethod.PATCH)
    public ResponseEntity updateUserEmail(@RequestParam String email, @RequestParam String newEmail,
                                                @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidEmail(newEmail)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("%s is invalid email!", newEmail));
        }

        if (!authenticationService.isValidToken(email, token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must be logged in first!");
        }

        User savedUser = userService.updateUserEmail(email, newEmail);
        authenticationService.updateTokenEmailKey(email, newEmail);

        return ResponseEntity.ok(savedUser);
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public ResponseEntity updateUserPassword(@RequestParam String email, @RequestParam String password,
                                   @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidPassword(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("%s is invalid password!", password));
        }

        if (!authenticationService.isValidToken(email, token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must be logged in first!");
        }

        return ResponseEntity.ok(userService.updateUserPassword(email, password));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam String email, @RequestHeader String token) throws IOException {
        if (!authenticationService.isValidToken(email, token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must be logged in first!");
        }

        userService.deleteUser(email);

        return ResponseEntity.ok(String.format("User with email: %s was successfully deleted.", email));
    }
}