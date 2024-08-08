package br.com.postech.techchallengeorder.adapters.consumer;

import static org.apache.commons.text.StringEscapeUtils.unescapeJson;

import br.com.postech.techchallengeorder.adapters.consumer.message.PaymentApprovalMessage;
import br.com.postech.techchallengeorder.adapters.consumer.message.PaymentCreatedMessage;
import br.com.postech.techchallengeorder.core.usecase.UpdateOrderPaymentApprovalUseCase;
import br.com.postech.techchallengeorder.core.usecase.UpdateOrderPaymentUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentConsumer {

  private final ObjectMapper objectMapper;
  private final UpdateOrderPaymentUseCase updateOrderPaymentUseCase;
  private final UpdateOrderPaymentApprovalUseCase updateOrderPaymentApprovalUseCase;

  @SqsListener(value = "${events.payments-creation-queue}", deletionPolicy = SqsMessageDeletionPolicy.NO_REDRIVE)
  @Transactional
  public void consumePaymentCreation(Message<String> message) throws JsonProcessingException {
    log.info("Payment created: {} received", message.getPayload());
    PaymentCreatedMessage payment = objectMapper.readValue(message.getPayload(), PaymentCreatedMessage.class);
    updateOrderPaymentUseCase.execute(payment.getOrderId(), payment.getId());
  }

  @SqsListener(value = "${events.payments-approval-queue}", deletionPolicy = SqsMessageDeletionPolicy.NO_REDRIVE)
  @Transactional
  public void consumePaymentApproval(Message<String> message) throws JsonProcessingException {
    log.info("Payment approval: {} received", message.getPayload());
    PaymentApprovalMessage payment = objectMapper.readValue(unescapeJson(message.getPayload()), PaymentApprovalMessage.class);
    updateOrderPaymentApprovalUseCase.execute(payment.getOrderId(), payment.getPaymentStatus());
  }

}
