package br.com.postech.techchallengeorder.adapters.consumer.message;

import br.com.postech.techchallengeorder.core.domain.entity.PaymentStatus;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentCreatedMessage {
  private String orderId;
  private String id;
  private PaymentStatus status;
  private BigDecimal amount;
}
