package br.com.postech.techchallengeorder.core.domain.entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Payment {
  private String id;
  private String orderId;
  private String cpf;
  private PaymentStatus status;
  private BigDecimal amount;
}
