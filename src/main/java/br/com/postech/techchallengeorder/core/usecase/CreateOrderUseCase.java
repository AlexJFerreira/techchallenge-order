package br.com.postech.techchallengeorder.core.usecase;


import br.com.postech.techchallengeorder.core.domain.entity.Order;

public interface CreateOrderUseCase {
  Order execute(Order order);

}
