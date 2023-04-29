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

import java.util.Collections;
import java.util.List;

import static steps.BaseSteps.*;

/**
 * Тест создание заказа
 *
 * @author  smirnov sergey
 * @since   28.04.2023
 */
public class CreateOrderTest {

    private IClient iClient; // утилитарный объект для работы с ингредиентами
    private OrderClient orderClient; // утилитарный объект для работы с заказами
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
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Попытка создания заказа с пустым списком ингредиентов")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderWithoutIngredientListTest() {
        ValidatableResponse response = orderClient.create(new Order());
        checkResponse(response, HttpStatus.SC_BAD_REQUEST, false, "Ingredient ids must be provided");
    }

    @Epic(value = "Заказ")
    @Feature(value = "Создание заказа")
    @Test
    @DisplayName("Создание заказа c ингредиентами без авторизации")
    @Description("Создание заказа с валидным списком ингредиентов без авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderValidIngredientListWithoutAuthorizationTest() {
        Collections.shuffle(hashes); // перемешивает хэш-коды ингредиентов
        ValidatableResponse response = orderClient.create(new Order(hashes.subList(0, 3))); // создает заказ, используя 3 случайных ингредиента
        checkResponse(response, HttpStatus.SC_OK, true);
        checkRespBodyItemsListIsNotNull(response, List.of("name", "order.number"));
    }

}