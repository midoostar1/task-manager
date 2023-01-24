package com.example.task_mamager.services;


import com.example.task_mamager.models.User;
import com.example.task_mamager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {


    private final UserRepository userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public User findById(long id) {
        return userDao.findById(id).get();
    }
}
