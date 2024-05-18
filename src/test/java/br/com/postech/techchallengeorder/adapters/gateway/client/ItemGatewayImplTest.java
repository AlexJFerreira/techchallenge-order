package br.com.postech.techchallengeorder.adapters.gateway.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeorder.core.domain.entity.Item;
import br.com.postech.techchallengeorder.core.gateway.database.OrderGateway;
import br.com.postech.techchallengeorder.core.usecase.impl.ChangeOrderStatusUseCaseImpl;
import br.com.postech.techchallengeorder.infra.ModelMapperConfig;
import br.com.postech.techchallengeorder.utils.OrderTestProvider;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ItemGatewayImplTest extends OrderTestProvider {

  @BeforeEach
  public void beforeEach() {
    ReflectionTestUtils.setField(itemGateway, "modelMapper", new ModelMapperConfig().modelMapper());
  }


  @InjectMocks
  private ItemGatewayImpl itemGateway;

  @Mock
  private ItemClient itemClient;

  @Test
  void searchItemsByIdsWithSuccess() {
    //Arrange
    var itemIds = List.of(1);
    var itemsResponse = getFakeItemSearchResponseList();

    when(itemClient.searchItemsByIds(itemIds))
        .thenReturn(itemsResponse);

    //Act
    List<Item> items = itemGateway.searchItemsByIds(itemIds);

    //Assert
    verify(itemClient).searchItemsByIds(itemIds);
    assertNotNull(items);
    assertFalse(items.isEmpty());
  }
}