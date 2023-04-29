package order;

import controllers.ingredient.IClient;
import controllers.order.OrderClient;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.pojo.Order;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static steps.BaseSteps.*;

/**
 * Тест создание заказа
 *
 * @author  smirnov sergey
 * @since   28.04.2023
 */
public class CreateOrderTest {

    private IClient iClient;
    private OrderClient orderClient;
    private List<String> hashes; // лист хэш-кодов ингредиентов

    @Before
    public void setup() {
        iClient = new IClient();
        orderClient = new OrderClient();
        hashes = iClient.getHashes();
    }

    @Epic(value = "Заказ")
    @Feature(value = "Создание заказа")
    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Базовый тест эндпоинта: /api/orders")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderWithoutAutorizationTest() {
        ValidatableResponse response = orderClient.create(new Order());
        checkResponce(response, HttpStatus.SC_BAD_REQUEST, false);
    }

}