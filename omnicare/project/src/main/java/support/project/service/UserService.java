package support.project.service;

import java.util.List;

import support.project.entity.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);
    List<User> getAllUsers();
}