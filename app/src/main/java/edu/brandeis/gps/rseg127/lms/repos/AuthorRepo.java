package edu.brandeis.gps.rseg127.lms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.brandeis.gps.rseg127.lms.entities.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
