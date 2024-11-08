package com.huyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huyle.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
