package br.com.postech.techchallengeorder.core.usecase.impl;

import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.ListOrderUseCase;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListOrderUseCaseImpl implements ListOrderUseCase {

  private final OrderGateway orderGateway;

  @Override
  public List<Order> execute() {
    return orderGateway.searchOrders();
  }
}
