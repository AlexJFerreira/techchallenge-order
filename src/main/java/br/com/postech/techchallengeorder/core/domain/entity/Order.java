package br.com.postech.techchallengeorder.core.domain.entity;

import static br.com.postech.techchallengeorder.core.domain.entity.PaymentStatus.APPROVED;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Order {

  private Integer id;
  private String cpf;
  private List<OrderItem> orderItems;
  private OrderStatus status;
  private LocalDateTime orderDate;
  private LocalDateTime lastUpdateDate;
  private String paymentId;

  public boolean isOrderWithIdentification() {
    return isNotBlank(cpf);
  }

  public void changeStatusByPayment(PaymentStatus paymentStatus) {
    if (paymentStatus.equals(APPROVED)) {
      this.setStatus(OrderStatus.PREPARING);
    } else {
      this.setStatus(OrderStatus.PAYMENT_ERROR);
    }

  }

}
