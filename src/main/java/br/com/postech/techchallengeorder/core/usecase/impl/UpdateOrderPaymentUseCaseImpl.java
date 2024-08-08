package br.com.postech.techchallengeorder.core.usecase.impl;

import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.UpdateOrderPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOrderPaymentUseCaseImpl implements UpdateOrderPaymentUseCase {

  private final OrderGateway orderGateway;

  @Override
  public void execute(String orderId, String paymentId) {
    Order order = orderGateway.searchOrderById(orderId);
    order.setPaymentId(paymentId);
    orderGateway.updateOrder(order);
  }
}
