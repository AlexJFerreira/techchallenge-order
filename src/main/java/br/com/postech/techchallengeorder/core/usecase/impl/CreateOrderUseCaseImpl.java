package br.com.postech.techchallengeorder.core.usecase.impl;


import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.entity.OrderItem;
import br.com.postech.techchallengeorder.core.domain.entity.Payment;
import br.com.postech.techchallengeorder.core.exceptions.NotFoundException;
import br.com.postech.techchallengeorder.core.gateway.client.ItemGateway;
import br.com.postech.techchallengeorder.core.gateway.client.PaymentGateway;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.CreateOrderUseCase;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

  private final OrderGateway orderGateway;
  private final PaymentGateway paymentGateway;
  private final ItemGateway itemGateway;

  @Override
  public Order execute(Order order) {
    try {
      List<Item> items = getItems(order);
      Order newOrder = orderGateway.createOrder(order, items);

      BigDecimal amount = newOrder.getOrderItems().stream()
          .map(OrderItem::getTotalPrice)
          .reduce(BigDecimal.ZERO, BigDecimal::add);

      Payment payment = paymentGateway.createPayment(newOrder.getId(), newOrder.getCpf(), amount);
      newOrder.setPaymentId(payment.getId());
      return orderGateway.updateOrder(newOrder);
    } catch (IllegalArgumentException e) {
      throw new NotFoundException("Pedido com item não existente, revise o pedido!");
    }
  }

  private List<Item> getItems(Order order) {
    var itemIds = order.getOrderItems()
        .stream()
        .map(OrderItem::getItemId)
        .toList();

    return itemGateway.searchItemsByIds(itemIds);

  }
}
