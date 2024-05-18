package br.com.postech.techchallengeorder.utils;


import br.com.postech.techchallengeorder.adapters.gateway.client.response.ItemSearchResponse;
import br.com.postech.techchallengeorder.adapters.gateway.client.response.PaymentCreationResponse;
import br.com.postech.techchallengeorder.adapters.gateway.database.entity.OrderEntity;
import br.com.postech.techchallengeorder.adapters.gateway.database.entity.OrderItemEntity;
import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.domain.entity.ItemType;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.entity.OrderItem;
import br.com.postech.techchallengeorder.core.domain.entity.Payment;
import br.com.postech.techchallengeorder.core.domain.entity.PaymentStatus;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public abstract class OrderTestProvider {

  public Order getFakeOutputOrder() {
    OrderItem orderItem = new OrderItem();
    orderItem.setId(1);
    orderItem.setQuantity(2);
    orderItem.setItemId(1);
    orderItem.setTotalPrice(new BigDecimal("10.00"));

    Order order = new Order();
    order.setPaymentId("e7a23b6e-b2d6-49d1-9170-63512253454b");
    order.setId(1);
    order.setCpf("38594576080");
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.PREPARING);
    order.setLastUpdateDate(LocalDateTime.now());
    order.setOrderItems(List.of(orderItem));

    return order;
  }

  public OrderEntity getFakeOrderEntity() {
    OrderItemEntity orderItem = new OrderItemEntity();
    orderItem.setId(1);
    orderItem.setQuantity(2);
    orderItem.setItemId(1);
    orderItem.setTotalPrice(new BigDecimal("10.00"));

    OrderEntity order = new OrderEntity();
    order.setPaymentId("e7a23b6e-b2d6-49d1-9170-63512253454b");
    order.setId(1);
    order.setCpf("38594576080");
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.PREPARING);
    order.setLastUpdateDate(LocalDateTime.now());
    order.setOrderItems(List.of(orderItem));

    return order;
  }

  public Order getFakeInputOrder() {
    OrderItem orderItem = new OrderItem();
    orderItem.setId(1);
    orderItem.setQuantity(2);
    orderItem.setItemId(1);
    orderItem.setTotalPrice(new BigDecimal("10.00"));

    Order order = new Order();
    order.setPaymentId("e7a23b6e-b2d6-49d1-9170-63512253454b");
    order.setCpf("38594576080");
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.RECEIVED);
    order.setLastUpdateDate(LocalDateTime.now());
    order.setOrderItems(List.of(orderItem));
    return order;
  }

  public List<Item> getFakeItemList() {
    Item item = new Item();
    item.setDescription("refrigerante");
    item.setName("Coca Cola");
    item.setType(ItemType.BEVERAGE);
    item.setPrice(new BigDecimal("5.0"));
    item.setId(1);
    return List.of(item);
  }

  public List<ItemSearchResponse> getFakeItemSearchResponseList() {
    ItemSearchResponse itemSearchResponse = new ItemSearchResponse();
    itemSearchResponse.setDescription("refrigerante");
    itemSearchResponse.setName("Coca Cola");
    itemSearchResponse.setType(ItemType.BEVERAGE);
    itemSearchResponse.setPrice(new BigDecimal("5.0"));
    itemSearchResponse.setId(1);
    return List.of(itemSearchResponse);
  }

  public Payment getFakePaymentOutput() {
    Payment payment = new Payment();
    payment.setId("e7a23b6e-b2d6-49d1-9170-63512253454b");
    payment.setCpf("38594576080");
    payment.setAmount(new BigDecimal("20.00"));
    payment.setStatus(PaymentStatus.PENDING);
    payment.setOrderId("1");
    return payment;
  }

  public Payment getFakePaymentInput() {
    Payment payment = new Payment();
    payment.setCpf("38594576080");
    payment.setAmount(new BigDecimal("20.00"));
    payment.setStatus(PaymentStatus.PENDING);
    payment.setOrderId("1");
    return payment;
  }

  public PaymentCreationResponse getFakePaymentICreationResponse() {
    PaymentCreationResponse payment = new PaymentCreationResponse();
    payment.setId("e7a23b6e-b2d6-49d1-9170-63512253454b");
    payment.setOrderId("1");
    return payment;
  }

}
