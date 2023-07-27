package com.example.minor_project1.controllers;


import com.example.minor_project1.dtos.CreateBookRequest;
import com.example.minor_project1.dtos.SearchBookRequest;
import com.example.minor_project1.models.Book;
import com.example.minor_project1.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookServices bookServices;

    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookRequest bookRequest){
        bookServices.createOrUpdate(bookRequest.to());

    }

    @GetMapping("/book")
    public List<Book> getBook(@RequestBody @Valid SearchBookRequest searchRequest ) throws Exception {
        return bookServices.getByRequest(searchRequest.getKey(),searchRequest.getValue());
    }


//    @GetMapping("/book")
//    public Book getBook(@RequestParam("Id") Integer id){
//        return bookServices.get(id);
//    }

}