package br.com.postech.techchallengeorder.adapters.gateway.client;

import br.com.postech.techchallengeorder.adapters.gateway.client.request.PaymentRequest;
import br.com.postech.techchallengeorder.adapters.gateway.client.response.PaymentCreationResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "payment", url = "http://techchallenge-payments-env.eba-iwmfvtxu.us-east-2.elasticbeanstalk.com")
public interface PaymentClient {

  @PostMapping(value = "/techchallenge/payments")
  PaymentCreationResponse createPayment(@RequestBody PaymentRequest paymentRequest);
}