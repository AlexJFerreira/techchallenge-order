package br.com.postech.techchallengeorder.adapters.gateway.client;

import br.com.postech.techchallengeorder.adapters.gateway.client.request.PaymentRequest;
import br.com.postech.techchallengeorder.core.domain.entity.Payment;
import br.com.postech.techchallengeorder.core.gateway.client.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

  private final PaymentClient paymentClient;
  private final ModelMapper modelMapper;

  @Override
  public Payment createPayment(Payment payment) {
    var paymentRequest = modelMapper.map(payment, PaymentRequest.class);
    return modelMapper.map(paymentClient.createPayment(paymentRequest), Payment.class);
  }
}
