package com.candu.ecommerce.orderline;

import com.candu.ecommerce.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
