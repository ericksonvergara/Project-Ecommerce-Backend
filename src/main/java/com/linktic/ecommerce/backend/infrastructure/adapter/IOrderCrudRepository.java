package com.linktic.ecommerce.backend.infrastructure.adapter;

import com.linktic.ecommerce.backend.domain.model.OrderState;
import com.linktic.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.linktic.ecommerce.backend.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity oe SET oe.orderState = :state WHERE oe.id = :id")
    void updateStateById(Integer id, OrderState state);

    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);
}
