package br.com.postech.techchallengeorder.core.gateway.client;

import br.com.postech.techchallengeorder.core.domain.entity.Payment;

public interface PaymentGateway {
  Payment createPayment(Payment payment);
}
