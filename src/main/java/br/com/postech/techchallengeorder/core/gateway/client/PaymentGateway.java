package br.com.postech.techchallengeorder.core.gateway.client;

import br.com.postech.techchallengeorder.core.domain.entity.Payment;
import java.math.BigDecimal;

public interface PaymentGateway {
  Payment createPayment(Integer orderId, String cpf, BigDecimal amount);
}
