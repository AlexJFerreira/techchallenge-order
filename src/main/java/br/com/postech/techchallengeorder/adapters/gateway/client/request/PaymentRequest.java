package br.com.postech.techchallengeorder.adapters.gateway.client.request;

import lombok.Data;

@Data
public class PaymentRequest {
  private final String orderId;
  private final String cpf;
}
