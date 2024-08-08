package br.com.postech.techchallengeorder.core.usecase.impl;


import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.entity.OrderItem;
import br.com.postech.techchallengeorder.core.gateway.client.ItemGateway;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.gateway.producer.OrderProducerGateway;
import br.com.postech.techchallengeorder.core.usecase.CreateOrderUseCase;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

  private final OrderGateway orderGateway;
  private final ItemGateway itemGateway;
  private final OrderProducerGateway orderProducerGateway;

  @Override
  public Order execute(Order order) {
      List<Item> items = getItems(order);
      Order newOrder = orderGateway.createOrder(order, items);

      BigDecimal amount = newOrder.getOrderItems().stream()
          .map(OrderItem::getTotalPrice)
          .reduce(BigDecimal.ZERO, BigDecimal::add);

    orderProducerGateway.notifyQueue(newOrder.getId().toString(), newOrder.getCpf(), amount);
    return newOrder;
    }


  private List<Item> getItems(Order order) {
    var itemIds = order.getOrderItems()
        .stream()
        .map(OrderItem::getItemId)
        .toList();

    return itemGateway.searchItemsByIds(itemIds);

  }
}
