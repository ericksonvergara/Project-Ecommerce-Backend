package com.linktic.ecommerce.backend.application;

import com.linktic.ecommerce.backend.domain.model.User;
import com.linktic.ecommerce.backend.domain.port.IUserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final IUserRepository iUserRepository;

    public User save(User user){
        return iUserRepository.save(user);
    }

    public User findById(Integer id){
        return iUserRepository.findById(id);
    }

    public User findByEmail(String email){
        return iUserRepository.findByEmail(email);
    }

}
