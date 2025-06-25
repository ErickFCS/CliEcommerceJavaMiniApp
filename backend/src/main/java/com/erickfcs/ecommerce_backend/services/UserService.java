package com.erickfcs.ecommerce_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickfcs.ecommerce_backend.models.Users;
import com.erickfcs.ecommerce_backend.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<Users> getAll() {
        return repo.findAll();
    }

    public Users getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Users create(Users user) {
        return repo.save(user);
    }

    public Users update(Integer id, Users user) {
        if (repo.existsById(id)) {
            user.setId(id);
            return repo.save(user);
        }
        return null;
    }

    public Boolean delete(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
