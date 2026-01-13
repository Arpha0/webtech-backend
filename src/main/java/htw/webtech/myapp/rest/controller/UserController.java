package htw.webtech.myapp.rest.controller;

import htw.webtech.myapp.business.service.UserService;
import htw.webtech.myapp.persistence.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    public UserController(UserService s) { this.service = s; }

    @PostMapping("/register")
    public User register(@RequestBody User u) { return service.register(u); }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginData) {
        User user = service.login(loginData.getUsername(), loginData.getPassword());
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.status(401).build();
    }

    // NEU: Endpunkt für den Passwort-Reset
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody User resetData) {
        service.resetPassword(resetData.getUsername(), resetData.getPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<User> getAll() { return service.getAll(); }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { service.deleteUser(id); }
}