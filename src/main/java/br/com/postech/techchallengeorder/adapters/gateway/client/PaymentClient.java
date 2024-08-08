package br.com.postech.techchallengeorder.adapters.gateway.client;

import br.com.postech.techchallengeorder.adapters.gateway.client.request.PaymentRequest;
import br.com.postech.techchallengeorder.adapters.gateway.client.response.PaymentCreationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "payment", url = "http://techchallenge-payments-env.eba-3mghac55.us-east-1.elasticbeanstalk.com")
public interface PaymentClient {

  @PostMapping(value = "/techchallenge/payments")
  PaymentCreationResponse createPayment(@RequestBody PaymentRequest paymentRequest);
}