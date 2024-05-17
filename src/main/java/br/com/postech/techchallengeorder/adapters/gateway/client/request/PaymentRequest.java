package br.com.postech.techchallengeorder.adapters.gateway.client.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {
  private final String orderId;
  private final String cpf;
  private final BigDecimal amount;
}
