package edu.brandeis.gps.rseg127.lms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.brandeis.gps.rseg127.lms.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
