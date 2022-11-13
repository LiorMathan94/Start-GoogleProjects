package AuthApp.controller;

import AuthApp.BaseResponse;
import AuthApp.service.AuthenticationService;
import AuthApp.service.User;
import AuthApp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public BaseResponse<User> updateUserName(@RequestParam String email, @RequestParam String name,
                                             @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidName(name)) {
            return BaseResponse.failure("Invalid name!");
        }

        if (!authenticationService.isValidToken(email, token)) {
            return BaseResponse.failure("You must be logged in first!");
        }

        return BaseResponse.success(userService.updateUserName(email, name));
    }

    @RequestMapping(value = "email", method = RequestMethod.PATCH)
    public BaseResponse<User> updateUserEmail(@RequestParam String email, @RequestParam String newEmail,
                                                @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidEmail(newEmail)) {
            return BaseResponse.failure(String.format("%s is invalid email!", newEmail));
        }

        if (!authenticationService.isValidToken(email, token)) {
            return BaseResponse.failure("You must be logged in first!");
        }

        User savedUser = userService.updateUserEmail(email, newEmail);
        authenticationService.updateTokenEmailKey(email, newEmail);

        return BaseResponse.success(savedUser);
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public BaseResponse<User> updateUserPassword(@RequestParam String email, @RequestParam String password,
                                   @RequestHeader String token) throws IOException {
        if (!InputValidation.isValidPassword(password)) {
            return BaseResponse.failure(String.format("%s is invalid password!", password));
        }

        if (!authenticationService.isValidToken(email, token)) {
            return BaseResponse.failure("You must be logged in first!");
        }

        return BaseResponse.success(userService.updateUserPassword(email, password));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public BaseResponse<User> deleteUser(@RequestParam String email, @RequestHeader String token) throws IOException {
        if (!authenticationService.isValidToken(email, token)) {
            return BaseResponse.failure("You must be logged in first!");
        }

        userService.deleteUser(email);

        return BaseResponse
                .noContent(true, String.format("User with email: %s was successfully deleted.", email));
    }
}