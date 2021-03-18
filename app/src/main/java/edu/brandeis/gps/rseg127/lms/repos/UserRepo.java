package edu.brandeis.gps.rseg127.lms.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.brandeis.gps.rseg127.lms.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);
}
