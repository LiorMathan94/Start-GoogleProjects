package AuthApp.controller;

import AuthApp.BaseResponse;
import AuthApp.service.AuthenticationService;
import AuthApp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticationController() {
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResponse<User> register(@RequestBody User user) {
        if (!InputValidation.isValidEmail(user.getEmail())) {
            return BaseResponse.failure("Invalid email address!");
        }
        if (!InputValidation.isValidName(user.getName())) {
            return BaseResponse.failure("Invalid name!");
        }
        if (!InputValidation.isValidPassword(user.getPassword())) {
            return BaseResponse.failure("Invalid password!");
        }

        return BaseResponse.success(this.authenticationService
                .register(user.getEmail(), user.getName(), user.getPassword()));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse<String> login(@RequestParam String email, @RequestParam String password) {
        if (!InputValidation.isValidEmail(email)) {
            return BaseResponse.failure("Your email address is invalid!");
        }

        return BaseResponse.success(this.authenticationService.login(email, password));
    }
}
