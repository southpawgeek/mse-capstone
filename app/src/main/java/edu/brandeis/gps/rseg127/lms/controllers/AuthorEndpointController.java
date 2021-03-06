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

import edu.brandeis.gps.rseg127.lms.entities.Author;
import edu.brandeis.gps.rseg127.lms.services.AuthorService;

@RestController
public class AuthorEndpointController {
    @Autowired
    private AuthorService authorService;

    @PostMapping(consumes="application/json", produces="application/json", path="/api/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/api/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable Integer id) {
        // pull up a copy of the current author
        Author updated_author = authorService.getAuthor(id);

        // fill in the blanks with submitted data
        updated_author.setFirstName(author.getFirstName());
        updated_author.setMiddleName(author.getMiddleName());
        updated_author.setLastName(author.getLastName());
        return new ResponseEntity<>(authorService.updateAuthor(updated_author), HttpStatus.CREATED);
    }

    @GetMapping(path="/api/authors/{id}", produces="application/json")
    public ResponseEntity<Author> getAuthor(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
    }

    @GetMapping(path="/api/authors", produces="application/json")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @DeleteMapping(path="/api/authors/{id}", produces="application/json")
    public ResponseEntity<String> deleteAuthor(@PathVariable(value="id") Integer id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
