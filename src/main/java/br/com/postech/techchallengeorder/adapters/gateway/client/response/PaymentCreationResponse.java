package br.com.postech.techchallengeorder.adapters.gateway.client.response;

import lombok.Data;

@Data
public class PaymentCreationResponse {
  private String orderId;
  private String id;
}
