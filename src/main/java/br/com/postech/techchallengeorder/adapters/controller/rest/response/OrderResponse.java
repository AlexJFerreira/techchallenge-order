package br.com.postech.techchallengeorder.adapters.controller.rest.response;


import br.com.postech.techchallengeorder.core.domain.entity.OrderItem;
import br.com.postech.techchallengeorder.core.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private Integer id;
    private String cpf;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastUpdateDate;
}
