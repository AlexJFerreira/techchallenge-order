package br.com.postech.techchallengeorder.core.usecase;

import br.com.postech.techchallengeorder.core.domain.entity.Order;

public interface UpdateOrderPaymentUseCase {
  void execute(String orderId, String paymentId);
}
