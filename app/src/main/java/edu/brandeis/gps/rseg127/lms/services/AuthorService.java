package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.Author;
import edu.brandeis.gps.rseg127.lms.repos.AuthorRepo;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }

    public Author updateAuthor(Author author) {
        return authorRepo.save(author);
    }

    public Author getAuthor(Integer id) {
        return authorRepo.findById(id).get();
    }

    public void deleteAuthor(Integer id) {
        authorRepo.deleteById(id);
    }
}
