package com.linktic.ecommerce.backend.infrastructure.adapter;

import com.linktic.ecommerce.backend.domain.model.Order;
import com.linktic.ecommerce.backend.domain.model.OrderState;
import com.linktic.ecommerce.backend.domain.port.IOrderRepository;
import com.linktic.ecommerce.backend.infrastructure.entity.OrderEntity;
import com.linktic.ecommerce.backend.infrastructure.entity.UserEntity;
import com.linktic.ecommerce.backend.infrastructure.mapper.IOrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderCrudRepositoryImpl implements IOrderRepository {
    private final IOrderMapper iOrderMapper;
    private final IOrderCrudRepository iOrderCrudRepository;
    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);
        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );
        return iOrderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return iOrderMapper.toOrder(iOrderCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La orden con id: "+id+ " no encontrado")
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return iOrderMapper.toOrderList(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return iOrderMapper.toOrderList(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if (state.equals(OrderState.CANCELED)){
            iOrderCrudRepository.updateStateById(id,OrderState.CANCELED);
        }else {
            iOrderCrudRepository.updateStateById(id,OrderState.CONFIRMED);
        }
    }
}
