package com.example.minor_project1.controllers;
import com.example.minor_project1.services.AdminServices;
import com.example.minor_project1.dtos.CreateAdminRequest;
import com.example.minor_project1.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminServices adminServices;

    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid CreateAdminRequest adminRequest){
        adminServices.create(adminRequest.to());

    }

    @GetMapping("/admin")
    public Admin getAdmin(@RequestParam("Id") Integer id){

        return adminServices.get(id);
    }

    @GetMapping("/admin/all")
    public List<Admin> getAllAdmin(){
        return adminServices.getAll();
    }

}


