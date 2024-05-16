package br.com.postech.techchallengeorder.core.usecase;


import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;

public interface ChangeOrderStatusUseCase {
  Order execute(Integer orderId, OrderStatus orderStatus);
}
