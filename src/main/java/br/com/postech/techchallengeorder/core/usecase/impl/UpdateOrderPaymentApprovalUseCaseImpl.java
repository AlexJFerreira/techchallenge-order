package br.com.postech.techchallengeorder.core.usecase.impl;

import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.entity.PaymentStatus;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.UpdateOrderPaymentApprovalUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateOrderPaymentApprovalUseCaseImpl implements UpdateOrderPaymentApprovalUseCase {

  private final OrderGateway orderGateway;

  @Override
  public void execute(String orderId, PaymentStatus paymentStatus) {
    Order order = orderGateway.searchOrderById(orderId);
    order.changeStatusByPayment(paymentStatus);
    orderGateway.updateOrder(order);
  }
}
