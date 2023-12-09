package com.springSocial.userService.service;

import com.springSocial.userService.dto.PostList;
import com.springSocial.userService.entity.Post;
import com.springSocial.userService.entity.User;
import com.springSocial.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        List<User> userList = userRepository.findAll();
        for (User user: userList){
            user.setPosts(restTemplate.getForEntity("http://localhost:8081/post/"+user.getId(), PostList.class).getBody());
        }
        return userList;
    }

    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }

}
