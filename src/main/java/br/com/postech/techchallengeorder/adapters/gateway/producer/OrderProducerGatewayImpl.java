package br.com.postech.techchallengeorder.adapters.gateway.producer;

import br.com.postech.techchallengeorder.adapters.gateway.producer.message.OrderCreatedMessage;
import br.com.postech.techchallengeorder.core.gateway.producer.OrderProducerGateway;
import br.com.postech.techchallengeorder.infra.EventsConfig;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducerGatewayImpl implements OrderProducerGateway {
  private final EventsConfig config;
  private final QueueMessagingTemplate messagingTemplate;

  @Override
  public void notifyQueue(String orderId, String cpf, BigDecimal amount) {
    OrderCreatedMessage orderCreatedMessage = new OrderCreatedMessage(orderId, cpf, amount);
    log.info("Notifying queue {}", config.getOrdersQueue());
    messagingTemplate.convertAndSend(config.getOrdersQueue(), orderCreatedMessage);
  }
}
