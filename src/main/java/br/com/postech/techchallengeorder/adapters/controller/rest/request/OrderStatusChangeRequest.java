package br.com.postech.techchallengeorder.adapters.controller.rest.request;

import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusChangeRequest {
  private OrderStatus orderStatus;
}
