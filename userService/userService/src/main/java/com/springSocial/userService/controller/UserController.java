package com.springSocial.userService.controller;

import com.springSocial.userService.entity.User;
import com.springSocial.userService.exception.UserException;
import com.springSocial.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        ResponseEntity<User> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new UserException("Error in creating user");
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        ResponseEntity<User> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            throw new UserException("Error in updating user");
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            throw new UserException("Error in fetching users");
        }
        return responseEntity;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        ResponseEntity<String> responseEntity;
        try {
            userService.deleteUser(id);
            responseEntity = new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new UserException("Error in deleting user");
        }
        return responseEntity;
    }
}
