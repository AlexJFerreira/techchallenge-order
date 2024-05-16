package br.com.postech.techchallengeorder.core.domain.entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItem {
  private Integer itemId;
  private Integer quantity;
  private BigDecimal totalPrice;
}
