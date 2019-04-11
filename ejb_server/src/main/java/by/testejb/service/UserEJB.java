package by.testejb.service;

import by.testejb.entity.User;
import by.testejb.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserEJB {

    @Inject
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }
}
