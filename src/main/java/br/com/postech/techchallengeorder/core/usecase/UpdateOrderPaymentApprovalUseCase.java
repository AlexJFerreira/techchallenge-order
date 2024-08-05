package br.com.postech.techchallengeorder.core.usecase;

import br.com.postech.techchallengeorder.core.domain.entity.PaymentStatus;

public interface UpdateOrderPaymentApprovalUseCase {
  void execute(String orderId, PaymentStatus paymentStatus);
}
