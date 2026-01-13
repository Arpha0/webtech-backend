package htw.webtech.myapp.business.service;

import htw.webtech.myapp.persistence.User;
import htw.webtech.myapp.persistence.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository r) { this.repo = r; }

    public User register(User u) { return repo.save(u); }

    public List<User> getAll() { return repo.findAll(); }

    public void deleteUser(Long id) { repo.deleteById(id); }

    public User login(String username, String password) {
        User user = repo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // NEU: Methode zum Zurücksetzen/Ändern des Passworts
    public void resetPassword(String username, String newPassword) {
        User user = repo.findByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            repo.save(user);
        }
    }
}