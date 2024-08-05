package br.com.postech.techchallengeorder.adapters.gateway.client;

import br.com.postech.techchallengeorder.adapters.gateway.client.response.ItemSearchResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "item", url = "http://localhost:8083")
public interface ItemClient {

  @GetMapping(value = "/techchallenge/items")
  List<ItemSearchResponse> searchItemsByIds(@RequestParam List<Integer> ids);
}
