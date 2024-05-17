package br.com.postech.techchallengeorder.core.gateway.database;

import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import java.util.List;
import java.util.Optional;

public interface OrderGateway {
  Order createOrder(Order order, List<Item> items);

  List<Order> searchOrders();

  Optional<Order> changeOrderStatus(Integer orderId, OrderStatus orderStatus);

  Order updateOrder(Order newOrder);
}
