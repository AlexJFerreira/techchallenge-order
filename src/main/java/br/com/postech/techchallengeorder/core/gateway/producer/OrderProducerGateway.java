package br.com.postech.techchallengeorder.core.gateway.producer;

import java.math.BigDecimal;

public interface OrderProducerGateway {
  void notifyQueue(String orderId, String cpf, BigDecimal amount);
}
