package br.com.postech.techchallengeorder.adapters.gateway.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeorder.core.domain.entity.Payment;
import br.com.postech.techchallengeorder.infra.ModelMapperConfig;
import br.com.postech.techchallengeorder.utils.OrderTestProvider;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PaymentGatewayImplTest extends OrderTestProvider {

  @BeforeEach
  public void beforeEach() {
    ReflectionTestUtils.setField(paymentGatewayImpl, "modelMapper", new ModelMapperConfig().modelMapper());
  }


  @InjectMocks
  private PaymentGatewayImpl paymentGatewayImpl;

  @Mock
  private PaymentClient paymentClient;

  @Test
  void createPaymentWithSuccess() {
    //Arrange
    var orderId = 1;
    var cpf = "36227297836";
    var amount = new BigDecimal("100.0");
    var outputPayment = getFakePaymentICreationResponse();
    when(paymentClient.createPayment(any())).thenReturn(outputPayment);

    //Act
    Payment payment = paymentGatewayImpl.createPayment(orderId, cpf, amount);

    //Assert
    assertNotNull(payment);
    verify(paymentClient).createPayment(any());
  }
}