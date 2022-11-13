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
        validateUserExists(user);

        user.get().setName(name);
        return userRepository.update(user.get());
    }

    public User updateUserEmail(String email, String newEmail) {
        Optional<User> user = userRepository.getByEmail(email);
        validateUserExists(user);

        user.get().setEmail(newEmail);
        return userRepository.update(user.get());
    }

    public User updateUserPassword(String email, String password) {
        Optional<User> user = userRepository.getByEmail(email);
        validateUserExists(user);

        user.get().setPassword(password);
        return userRepository.update(user.get());
    }

    public void deleteUser(String email) {
        Optional<User> user = userRepository.getByEmail(email);
        validateUserExists(user);

        userRepository.delete(user.get());
    }

    private void validateUserExists(Optional<User> user) {
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User does not exists!");
        }
    }
}
