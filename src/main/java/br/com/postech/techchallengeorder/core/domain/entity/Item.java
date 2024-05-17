package br.com.postech.techchallengeorder.core.domain.entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Item {
  private Integer id;
  private String name;
  private String description;
  private BigDecimal price;
  private ItemType type;
}
