package br.com.postech.techchallengeorder.adapters.gateway.database.entity;

import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @JoinColumn(name = "cpf")
  private String cpf;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.RECEIVED;

  @Column(name = "order_date", insertable = false, updatable = false)
  private LocalDateTime orderDate;

  @Column(name = "last_update_date", insertable = false, updatable = false)
  private LocalDateTime lastUpdateDate;

  @Column(name = "payment_id")
  private String paymentId;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<OrderItemEntity> orderItems = new ArrayList<>();

  public void addOrderItem(OrderItemEntity orderItem) {
    this.orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

}