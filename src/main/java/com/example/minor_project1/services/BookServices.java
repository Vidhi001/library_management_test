package com.example.minor_project1.services;

import com.example.minor_project1.models.Author;
import com.example.minor_project1.models.Book;
import com.example.minor_project1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Arrays.*;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorServices authorService;

    private static Logger logger = LoggerFactory.getLogger(BookServices.class);
    public void createOrUpdate(Book bookRequest) {
        Author bookauthor = bookRequest.getMy_author();

        Author authorDetails = authorService.getOrCreate(bookauthor);

        bookRequest.setMy_author(authorDetails);

        bookRepository.save(bookRequest);


    }

//    public Book get(Integer id) {
//        return bookRepository.findById(id).orElse(null);
//
//    }

    public List<Book> getByRequest(String key,String value) throws Exception {

//        String key = searchRequest.getKey();
//
//        String value = searchRequest.getValue();

        switch (key){
            case "Id" :
                return asList(bookRepository.findById(Integer.parseInt(value)).orElse(null));
            case "bookName" :
                return bookRepository.findByBookName(value);

            case "genre" :
                return bookRepository.findByGenre(value);
            case "authorName" :
                return bookRepository.findByAuthorName(value);

            default:
                throw  new Exception("Search Key Not Match : "+ key);


        }
//        return bookRepository.findByFilter(searchRequest.getKey(), searchRequest.getValue());
    }
}
