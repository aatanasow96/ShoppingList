package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.entity.User;
import com.example.shoppinglist.entity.service.UserServiceModel;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        userRepository.save(mapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> mapper.map(user, UserServiceModel.class))
                .orElse(null);
    }
}
