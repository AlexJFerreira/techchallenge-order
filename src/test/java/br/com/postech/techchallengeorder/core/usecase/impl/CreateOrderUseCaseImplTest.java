package br.com.postech.techchallengeorder.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.gateway.client.ItemGateway;
import br.com.postech.techchallengeorder.core.gateway.client.PaymentGateway;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.utils.OrderTestProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest extends OrderTestProvider {

  @InjectMocks
  private CreateOrderUseCaseImpl createOrderUseCase;

  @Mock
  private OrderGateway orderGateway;

  @Mock
  private PaymentGateway paymentGateway;

  @Mock
  private ItemGateway itemGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    var inputOrder = getFakeInputOrder();
    var outPutOrder = getFakeOutputOrder();
    var payment = getFakePaymentOutput();
    var itemsList = getFakeItemList();

    when(itemGateway.searchItemsByIds(any()))
        .thenReturn(itemsList);

    when(orderGateway.createOrder(inputOrder, itemsList))
        .thenReturn(outPutOrder);

    when(paymentGateway.createPayment(any(), any(), any()))
        .thenReturn(payment);

    when(orderGateway.updateOrder(any()))
        .thenReturn(outPutOrder);

    //Act
    Order createdOrder = createOrderUseCase.execute(inputOrder);

    //Assert
    verify(orderGateway).createOrder(any(), any());
    verify(orderGateway).updateOrder(any());
    verify(itemGateway).searchItemsByIds(any());
    verify(paymentGateway).createPayment(any(), any(), any());
    assertNotNull(createdOrder);
  }

  @Test
  void executeWhenThereIsNoExistingItemInOrderThenThrowsNotFoundException() {
    //Arrange
    var inputOrder = getFakeInputOrder();
    var outPutOrder = getFakeOutputOrder();
    var payment = getFakePaymentOutput();
    var itemsList = getFakeItemList();

    when(itemGateway.searchItemsByIds(any()))
        .thenReturn(itemsList);

    when(orderGateway.createOrder(inputOrder, itemsList))
        .thenReturn(outPutOrder);

    when(paymentGateway.createPayment(any(), any(), any()))
        .thenReturn(payment);

    when(orderGateway.updateOrder(any()))
        .thenReturn(outPutOrder);

    //Act
    Order createdOrder = createOrderUseCase.execute(inputOrder);

    //Assert
    verify(orderGateway).createOrder(any(), any());
    verify(orderGateway).updateOrder(any());
    verify(itemGateway).searchItemsByIds(any());
    verify(paymentGateway).createPayment(any(), any(), any());
    assertNotNull(createdOrder);
  }
}