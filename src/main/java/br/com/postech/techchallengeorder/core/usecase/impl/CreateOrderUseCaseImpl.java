package br.com.postech.techchallengeorder.core.usecase.impl;


import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.exceptions.NotFoundException;
import br.com.postech.techchallengeorder.core.gateway.client.PaymentGateway;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.CreateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

  private final OrderGateway orderGateway;
  private final PaymentGateway paymentGateway;

  @Override
  public Order execute(Order order) {
    try {
      // 0. Criar order buscando os dados dos itens no itemsGateway
      // 1. Criar order sem paymentId
      // 2. Chamar endpoint para criar payment passando orderId.
      // 3. Atualizar order com paymentId
      // 4. Depois payment chamar endpoint para aprovar pagamento
      Order newOrder = orderGateway.createOrder(order);
      paymentGateway.createPayment(order.getCpf(), order.getTotalPrice(), order.getId())
      return null;
    } catch (IllegalArgumentException e) {
      throw new NotFoundException("Pedido com item não existente, revise o pedido!");
    }
  }
}
