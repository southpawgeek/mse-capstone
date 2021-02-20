package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.brandeis.gps.rseg127.lms.entities.User;
import edu.brandeis.gps.rseg127.lms.services.UserService;

@RestController
public class UserEndpointController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes="application/json", produces="application/json", path="/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/api/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        // pull up a copy of the current user
        User updated_user = userService.getUser(id);

        // fill in the blanks with submitted data
        //some things cannot be updated:
        // id, username, patron_id, creation_date
        updated_user.setPassword_hash(user.getPassword_hash());
        updated_user.setFirst_name(user.getFirst_name());
        updated_user.setMiddle_name(user.getMiddle_name());
        updated_user.setLast_name(user.getLast_name());
        updated_user.setHome_address(user.getHome_address());
        updated_user.setMail_address(user.getMail_address());
        updated_user.setEmail_address(user.getEmail_address());
        updated_user.setPhone1(user.getPhone1());
        updated_user.setPhone2(user.getPhone2());
        // no idea why lombok did this??
        updated_user.setPhone_3(user.getPhone_3());

        return new ResponseEntity<>(userService.updateUser(updated_user), HttpStatus.CREATED);
    }

    @GetMapping(path="/api/users/{id}", produces="application/json")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping(path="/api/users", produces="application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping(path="/api/users/{id}", produces="application/json")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
