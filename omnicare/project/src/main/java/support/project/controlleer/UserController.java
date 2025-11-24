package support.project.controlleer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import support.project.entity.User;
import support.project.entity.Dtos.ActivityLogDTO;
import support.project.service.UserService;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import support.project.service.ActivityLogService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityLogService logService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User savedUser = userService.registerUser(user);
            response.put("success", true);
            response.put("message", "User registered successfully!");
            response.put("data", savedUser);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Registration failed: " + e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody User user, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User existingUser = userService.loginUser(user.getUsername(), user.getPassword());

        if (existingUser != null) {

            // ⭐ Save login event
            ActivityLogDTO dto = new ActivityLogDTO();
            dto.setUsername(user.getUsername());
            dto.setAction("LOGIN");
            dto.setDetails("User logged in successfully");
            dto.setPage("/api/users/login");
            dto.setIp(request.getRemoteAddr());
            logService.saveLog(dto);

            response.put("success", true);
            response.put("message", "Login successful!");
            response.put("username", user.getUsername());  // ⭐ Return username to frontend

        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password");
        }
        return response;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
