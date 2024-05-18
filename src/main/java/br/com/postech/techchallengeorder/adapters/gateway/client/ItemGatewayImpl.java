package br.com.postech.techchallengeorder.adapters.gateway.client;

import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.gateway.client.ItemGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemGatewayImpl implements ItemGateway {

  private final ItemClient itemClient;
  private final ModelMapper modelMapper;

  @Override
  public List<Item> searchItemsByIds(List<Integer> ids) {
    var items = itemClient.searchItemsByIds(ids);
    return items.stream()
        .map(itemEntity -> modelMapper.map(itemEntity, Item.class))
        .toList();
  }
}
