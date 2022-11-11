package AuthApp.controller;

import AuthApp.service.AuthenticationService;
import AuthApp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticationController() {
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
        if (!InputValidation.isValidEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address!");
        }
        if (!InputValidation.isValidName(user.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name!");
        }
        if (!InputValidation.isValidPassword(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password!");
        }

        return ResponseEntity.ok(this.authenticationService
                .register(user.getEmail(), user.getName(), user.getPassword()));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        if (!InputValidation.isValidEmail(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your email address is invalid!");
        }

        return ResponseEntity.ok(this.authenticationService.login(email, password));
    }
}
