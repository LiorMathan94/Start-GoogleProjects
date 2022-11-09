package AuthApp.service;

import AuthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserService() {
    }

    public User updateUserName(String email, String name) {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            user.get().setName(name);
            return userRepository.update(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address: %s does not exist", email));
        }
    }

    public User updateUserEmail(String email, String newEmail) {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            return userRepository.update(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    public User updateUserPassword(String email, String password) {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            user.get().setPassword(password);
            return userRepository.update(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    public void deleteUser(String email) {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }
}
