package br.com.postech.techchallengeorder.adapters.gateway.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeorder.adapters.gateway.client.PaymentClient;
import br.com.postech.techchallengeorder.adapters.gateway.client.PaymentGatewayImpl;
import br.com.postech.techchallengeorder.adapters.gateway.database.repository.OrderRepository;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import br.com.postech.techchallengeorder.infra.ModelMapperConfig;
import br.com.postech.techchallengeorder.utils.OrderTestProvider;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class OrderGatewayImplTest extends OrderTestProvider {

  @InjectMocks
  private OrderGatewayImpl orderGatewayImpl;

  @Mock
  private OrderRepository orderRepository;

  @BeforeEach
  public void beforeEach() {
    ReflectionTestUtils.setField(orderGatewayImpl, "modelMapper", new ModelMapperConfig().modelMapper());
  }


  @Test
  void createOrder() {
    //Arrange
    var inputOrder = getFakeInputOrder();
    var itemList = getFakeItemList();
    var orderEntity = getFakeOrderEntity();
    when(orderRepository.save(any())).thenReturn(orderEntity);

    //Act
    Order order = orderGatewayImpl.createOrder(inputOrder, itemList);

    //Assert
    verify(orderRepository).save(any());
    assertNotNull(order);
  }

  @Test
  void changeOrderStatusWithSuccess() {
    //Arrange
    var orderIdToChange = 1;
    var newOrderStatus = OrderStatus.DELIVERED;
    var orderEntity = getFakeOrderEntity();

    when(orderRepository.findById(orderIdToChange))
        .thenReturn(Optional.of(orderEntity));

    //Act
    Optional<Order> order =
        orderGatewayImpl.changeOrderStatus(orderIdToChange, newOrderStatus);

    //Assert
    verify(orderRepository)
        .findById(orderIdToChange);

    assertTrue(order.isPresent());

  }

  @Test
  void updateOrderWithSuccess() {
    //Arrange
    var order = getFakeOutputOrder();
    var orderEntity = getFakeOrderEntity();
    when(orderRepository.save(any())).thenReturn(orderEntity);

    //Act
    Order orderResponse = orderGatewayImpl.updateOrder(order);

    //Assert
    verify(orderRepository).save(any());
    assertNotNull(orderResponse);

  }
}