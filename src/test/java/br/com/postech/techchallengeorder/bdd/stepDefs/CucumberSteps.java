package br.com.postech.techchallengeorder.bdd.stepDefs;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import br.com.postech.techchallengeorder.adapters.gateway.database.repository.OrderRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements =
    {"insert into client_order (id, CPF, status, payment_id) values (1, '63451553066', 'RECEIVED', '6cfb1f97-cf51-451e-a5d9-debe4736dc4e')"},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@CucumberContextConfiguration
public class CucumberSteps {
  private Response response;

  @LocalServerPort
  private Integer port;

  @Autowired
  OrderRepository orderRepository;

  private static final String HOST = "http://localhost:";
  private static final String BASE_URL_PREFIX = "/techchallenge/orders";

  @When("client calls orders list endpoint")
  public void clientCallsOrdersListEndpoint() {
    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get(HOST + port + BASE_URL_PREFIX);
  }

  @Then("client receives order list data")
  public void clientReceivesOrderListData() {
    response.then()
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/ordersListResponse.json"));;
  }
}
