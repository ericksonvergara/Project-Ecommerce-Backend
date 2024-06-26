package com.linktic.ecommerce.backend.infrastructure.adapter;

import com.linktic.ecommerce.backend.domain.model.User;
import com.linktic.ecommerce.backend.domain.port.IUserRepository;
import com.linktic.ecommerce.backend.infrastructure.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserCrudRepositoryImpl implements IUserRepository {
    private final IUserCrudRepository iUserCrudRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(iUserCrudRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException ("User with email: "+email+ " not found")
        )   );
    }

    @Override
    public User findById(Integer id) {
        return userMapper.toUser(iUserCrudRepository.findById(id).get());
    }
}
