package br.com.postech.techchallengeorder.adapters.gateway.client;

import br.com.postech.techchallengeorder.adapters.gateway.client.request.PaymentRequest;
import br.com.postech.techchallengeorder.adapters.gateway.client.response.PaymentCreationResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "jplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface PaymentClient {

  @PostMapping(value = "/techchallenge/payments")
  List<PaymentCreationResponse> createPayment(@RequestBody PaymentRequest paymentRequest);
}