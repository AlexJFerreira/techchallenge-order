package br.com.postech.techchallengeorder.core.usecase.impl;


import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import br.com.postech.techchallengeorder.core.exceptions.NotFoundException;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.ChangeOrderStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeOrderStatusUseCaseImpl implements ChangeOrderStatusUseCase {

  private final OrderGateway orderGateway;

  @Override
  public Order execute(Integer orderId, OrderStatus orderStatus) {
    return orderGateway.changeOrderStatus(orderId, orderStatus)
        .orElseThrow(() -> new NotFoundException(String.format("Order with id %s not found", orderId)));
  }
}
