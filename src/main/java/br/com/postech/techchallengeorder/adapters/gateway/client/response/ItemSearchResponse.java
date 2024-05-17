package br.com.postech.techchallengeorder.adapters.gateway.client.response;

import br.com.postech.techchallengeorder.core.domain.entity.ItemType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemSearchResponse {
  private Integer id;
  private String name;
  private String description;
  private BigDecimal price;
  private ItemType type;
}
