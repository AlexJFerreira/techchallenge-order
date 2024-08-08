package br.com.postech.techchallengeorder.adapters.consumer.message;

import br.com.postech.techchallengeorder.core.domain.entity.PaymentStatus;
import lombok.Data;

@Data
public class PaymentApprovalMessage {
  PaymentStatus paymentStatus;
  String orderId;
}
