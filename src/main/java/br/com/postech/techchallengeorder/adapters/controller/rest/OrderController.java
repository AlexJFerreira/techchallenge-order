package br.com.postech.techchallengeorder.adapters.controller.rest;


import br.com.postech.techchallengeorder.adapters.controller.rest.request.OrderCreationRequest;
import br.com.postech.techchallengeorder.adapters.controller.rest.request.OrderStatusChangeRequest;
import br.com.postech.techchallengeorder.adapters.controller.rest.response.OrderResponse;
import br.com.postech.techchallengeorder.core.domain.entity.Order;
import br.com.postech.techchallengeorder.core.usecase.ChangeOrderStatusUseCase;
import br.com.postech.techchallengeorder.core.usecase.CreateOrderUseCase;
import br.com.postech.techchallengeorder.core.usecase.ListOrderUseCase;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/techchallenge/orders")
public class OrderController {

  private final CreateOrderUseCase createOrderUseCase;
  private final ListOrderUseCase listOrderUseCase;
  private final ChangeOrderStatusUseCase changeOrderStatusUseCase;
  private final ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Transactional
  public OrderResponse orderCreation(@Valid @RequestBody final OrderCreationRequest orderCreationRequest) {
    log.info("Order creation request: {} received", orderCreationRequest);
    var order = modelMapper.map(orderCreationRequest, Order.class);
    return modelMapper.map(createOrderUseCase.execute(order), OrderResponse.class);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<OrderResponse> listAllOrders() {
    log.info("Get all orders request received");
    var orders = listOrderUseCase.execute();
    return orders.stream()
        .map(order -> modelMapper.map(order, OrderResponse.class))
        .toList();
  }

  @PatchMapping("/{orderId}")
  @ResponseStatus(HttpStatus.OK)
  public OrderResponse orderStatusChange(@Valid @RequestBody final OrderStatusChangeRequest orderStatusChangeRequest,
                                         @NotNull @PathVariable final Integer orderId) {

    var newOrderstatus = orderStatusChangeRequest.getOrderStatus();

    log.info("Changing order {} to new status: {} ", orderId, newOrderstatus);
    return modelMapper.map(changeOrderStatusUseCase.execute(orderId, newOrderstatus), OrderResponse.class);
  }

}
