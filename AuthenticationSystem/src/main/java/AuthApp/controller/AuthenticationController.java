package AuthApp.controller;

import AuthApp.service.AuthenticationService;
import AuthApp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private static AuthenticationController singleInstance = null;
    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticationController() {
    }

    public static AuthenticationController getInstance() {
        if (singleInstance == null) {
            singleInstance = new AuthenticationController();
        }

        return singleInstance;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user) {
        if (!InputValidation.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email address!");
        }
        if (!InputValidation.isValidName(user.getName())) {
            throw new IllegalArgumentException("Invalid name!");
        }
        if (!InputValidation.isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password!");
        }

        return ResponseEntity.ok(this.authenticationService
                .register(user.getEmail(), user.getName(), user.getPassword()));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        if (!InputValidation.isValidEmail(email)) {
            throw new IllegalArgumentException("Your email address is invalid!");
        }

        return ResponseEntity.ok(this.authenticationService.login(email, password));
    }
}
