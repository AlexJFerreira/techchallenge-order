package br.com.postech.techchallengeorder.core.usecase;

public interface UpdateOrderPaymentUseCase {
  void execute(String orderId, String paymentId);
}
