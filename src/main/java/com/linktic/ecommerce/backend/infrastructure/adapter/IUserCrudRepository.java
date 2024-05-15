package com.linktic.ecommerce.backend.infrastructure.adapter;

import com.linktic.ecommerce.backend.infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Integer> {
}
