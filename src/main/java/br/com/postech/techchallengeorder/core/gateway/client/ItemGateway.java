package br.com.postech.techchallengeorder.core.gateway.client;


import br.com.postech.techchallengeorder.core.domain.entity.Item;
import java.util.List;

public interface ItemGateway {
  List<Item> searchItemsByIds(List<Integer> ids);

}
