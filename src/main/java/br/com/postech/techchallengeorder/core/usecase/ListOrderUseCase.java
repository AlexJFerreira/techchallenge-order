package br.com.postech.techchallengeorder.core.usecase;

import br.com.postech.techchallengeorder.core.domain.entity.Order;
import java.util.List;

public interface ListOrderUseCase {
    List<Order> execute();
}
