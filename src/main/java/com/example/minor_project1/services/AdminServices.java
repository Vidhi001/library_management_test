package com.example.minor_project1.services;

import com.example.minor_project1.models.Admin;
import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.minor_project1.repository.AdminRepository;

import java.util.List;

@Service
public class AdminServices {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;
    public void create(Admin admin) {
        SecuredUser securedUser = admin.getSecuredUser();
        userService.save(securedUser, Constants.ADMIN_USER);
        adminRepository.save(admin);

    }

    public Admin get(Integer id) {
        return adminRepository.findById(id).orElse(null);

    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }
}
