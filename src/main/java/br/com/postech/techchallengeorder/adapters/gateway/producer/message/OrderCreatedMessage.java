package br.com.postech.techchallengeorder.adapters.gateway.producer.message;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCreatedMessage {
  private final String orderId;
  private final String cpf;
  private final BigDecimal amount;
}
