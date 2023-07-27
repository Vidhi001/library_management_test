package com.example.minor_project1.services;

import com.example.minor_project1.models.Author;
import com.example.minor_project1.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServices {

    @Autowired
    AuthorRepository authorRepository;

    private static Logger logger = LoggerFactory.getLogger(AuthorServices.class);

    public Author getOrCreate(Author bookauthor) {

        Author AuthorDetail = authorRepository.findByEmail(bookauthor.getEmail());

        if(AuthorDetail==null){
            AuthorDetail = authorRepository.save(bookauthor);
        }
        return AuthorDetail;

    }
}

