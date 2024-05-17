package br.com.postech.techchallengeorder.adapters.gateway.database;


import static br.com.postech.techchallengeorder.core.domain.enums.OrderStatus.DELIVERED;
import static br.com.postech.techchallengeorder.core.domain.enums.OrderStatus.PREPARING;
import static br.com.postech.techchallengeorder.core.domain.enums.OrderStatus.READY_FOR_PICKUP;
import static br.com.postech.techchallengeorder.core.domain.enums.OrderStatus.RECEIVED;

import br.com.postech.techchallengeorder.adapters.gateway.database.entity.OrderEntity;
import br.com.postech.techchallengeorder.adapters.gateway.database.entity.OrderItemEntity;
import br.com.postech.techchallengeorder.adapters.gateway.database.repository.OrderRepository;
import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.entity.OrderItem;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

  private static final List<OrderStatus> PAYMENT_STATUS_ORDER = List.of(READY_FOR_PICKUP, PREPARING, RECEIVED);
  public static final Comparator<Order> PAYMENT_STATUS_COMPARATOR = Comparator.comparingInt(o -> {
    int index = PAYMENT_STATUS_ORDER.indexOf(o.getStatus());
    return index == -1 ? Integer.MAX_VALUE : index;
  });

  private final OrderRepository orderRepository;
  private final ModelMapper modelMapper;

  @Override
  public Order createOrder(Order order, List<Item> items) {

    var orderEntity = new OrderEntity();
    if (order.isOrderWithIdentification()) {
      orderEntity.setCpf(order.getCpf());
    }

     order.getOrderItems()
         .forEach(orderItem -> orderEntity.addOrderItem(buildOrderItemEntity(orderItem, items)));

    OrderEntity save = orderRepository.save(orderEntity);
    Order map = modelMapper.map(save, Order.class);
    return map;
  }

  @Override
  @Transactional
  public List<Order> searchOrders() {
    var allOrders = orderRepository.findAllByStatusNot(DELIVERED);
    return allOrders.stream()
        .map(order -> modelMapper.map(order, Order.class))
        .sorted((PAYMENT_STATUS_COMPARATOR)
            .thenComparing(Order::getOrderDate))
        .toList();
  }

  @Override
  @Transactional
  public Optional<Order> changeOrderStatus(Integer orderId, OrderStatus orderStatus) {
    var orderEntity = orderRepository.findById(orderId);
    orderEntity.ifPresent(order -> order.setStatus(orderStatus));
    return orderEntity.map(orderEntity1 -> modelMapper.map(orderEntity1, Order.class));
  }

  @Override
  public Order updateOrder(Order newOrder) {
    var orderEntity = modelMapper.map(newOrder, OrderEntity.class);
    return modelMapper.map(orderRepository.save(orderEntity), Order.class);
  }

  private OrderItemEntity buildOrderItemEntity(OrderItem orderItem, List<Item> items) {
    var item = items.stream().filter(itemEntity -> itemEntity.getId().equals(orderItem.getItemId())).findFirst().get();

    var itemQuantity = new BigDecimal(orderItem.getQuantity());
    var itemOrderPrice = item.getPrice().multiply(itemQuantity);

    return OrderItemEntity.builder()
        .itemId(item.getId())
        .quantity(orderItem.getQuantity())
        .totalPrice(itemOrderPrice).build();
  }
}
