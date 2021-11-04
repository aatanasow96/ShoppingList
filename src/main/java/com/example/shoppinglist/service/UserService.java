package com.example.shoppinglist.service;

import com.example.shoppinglist.entity.service.UserServiceModel;

public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);
}
