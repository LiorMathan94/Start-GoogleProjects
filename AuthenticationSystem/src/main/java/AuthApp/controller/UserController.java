package AuthApp.controller;

import AuthApp.service.AuthenticationService;
import AuthApp.service.User;
import AuthApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/user")
public class UserController {
    private static UserController singleInstance = null;
   @Autowired
    private UserService userService;
   @Autowired
   private AuthenticationService authenticationService;

    private UserController() {
    }

    public static UserController getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserController();
        }

        return singleInstance;
    }

    @RequestMapping(value = "name", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUserName(@RequestParam String email, @RequestParam String name,
                                               @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidName(name)) {
            return ResponseEntity.badRequest().build();
        }

        validateToken(email, token);
        return ResponseEntity.ok(userService.updateUserName(email, name));
    }

    @RequestMapping(value = "email", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUserEmail(@RequestParam String email, @RequestParam String newEmail,
                                                @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidEmail(newEmail)) {
            throw new IllegalArgumentException(String.format("%s is invalid email!", newEmail));
        }

        validateToken(email, token);
        User savedUser = userService.updateUserEmail(email, newEmail);
        authenticationService.updateTokenEmailKey(email, newEmail);

        return ResponseEntity.ok(savedUser);
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUserPassword(@RequestParam String email, @RequestParam String password,
                                   @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidPassword(password)) {
            throw new IllegalArgumentException(String.format("%s is invalid password!", password));
        }

        validateToken(email, token);
        return ResponseEntity.ok(userService.updateUserPassword(email, password));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam String email, @RequestHeader String token) throws IOException {
        validateToken(email, token);
        userService.deleteUser(email);

        return ResponseEntity.noContent().build();
    }

    private void validateToken(String email, String token) throws IOException {
        if (!authenticationService.isValidToken(email, token)) {
            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
        }
    }
}
