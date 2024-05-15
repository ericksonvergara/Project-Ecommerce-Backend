package com.linktic.ecommerce.backend.domain.port;

import com.linktic.ecommerce.backend.domain.model.User;
import org.mapstruct.control.MappingControl;

public interface IUserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
}
