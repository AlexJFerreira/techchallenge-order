package br.com.postech.techchallengeorder.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import br.com.postech.techchallengeorder.core.exceptions.NotFoundException;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.utils.OrderTestProvider;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChangeOrderStatusUseCaseImplTest extends OrderTestProvider {

  @InjectMocks
  private ChangeOrderStatusUseCaseImpl changeOrderStatusUseCase;

  @Mock
  private OrderGateway orderGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    var orderIdToChange = 1;
    var order = getFakeOutputOrder();
    when(orderGateway.changeOrderStatus(orderIdToChange, OrderStatus.PREPARING))
        .thenReturn(Optional.of(order));

    //Act
    Order orderChanged = changeOrderStatusUseCase.execute(orderIdToChange, OrderStatus.PREPARING);

    //Assert
    verify(orderGateway).changeOrderStatus(orderIdToChange, OrderStatus.PREPARING);
    assertNotNull(orderChanged);
  }

  @Test
  void executeWhenOrderIsNotFoundThenThrowsNotFoundException() {
    //Arrange
    var orderIdToChange = 1;
    var order = getFakeOutputOrder();
    when(orderGateway.changeOrderStatus(orderIdToChange, OrderStatus.PREPARING))
        .thenReturn(Optional.empty());

    //Act + Assert
    assertThrows(NotFoundException.class, () -> changeOrderStatusUseCase.execute(orderIdToChange, OrderStatus.PREPARING));

    //Assert
    verify(orderGateway).changeOrderStatus(orderIdToChange, OrderStatus.PREPARING);
  }
}