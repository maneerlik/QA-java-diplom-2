package order;

import controllers.ingredient.IngtClient;
import controllers.order.OrderClient;
import controllers.user.UserClient;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.pojo.Order;
import model.pojo.User;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static data.RandomUser.randomValidUser;
import static steps.BaseSteps.*;

/**
 * Тест создание заказа
 *
 * @author  smirnov sergey
 * @since   28.04.2023
 */
public class CreateOrderTest {

    private User user;
    private UserClient client; // утилитарный объект для работы с пользователем
    private OrderClient orderClient; // утилитарный объект для работы с заказами
    private List<String> hashes;

    @Before
    public void setup() {
        user = randomValidUser();
        client = new UserClient();
        orderClient = new OrderClient();
        hashes = new IngtClient().getHashes(); // получает хэш-коды ингредиентов
        Collections.shuffle(hashes); // перемешивает порядок хэш-кодов
    }

    @Epic(value = "Заказ")
    @Feature(value = "Создание заказа")
    @Test
    @DisplayName("Создание заказа c ингредиентами и без авторизации")
    @Description("Создание заказа с валидным списком ингредиентов и без авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderValidIngredientListWithoutAuthorizationTest() {
        ValidatableResponse response = orderClient.create(new Order(hashes.subList(0, 3))); // создает заказ, используя 3 случайных ингредиента
        checkResponse(response, HttpStatus.SC_UNAUTHORIZED, false);
    }

    @Epic(value = "Заказ")
    @Feature(value = "Создание заказа")
    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Попытка создания заказа с пустым списком ингредиентов")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderWithoutIngredientListTest() {
        String bearerToken = client.register(user).extract().path("accessToken").toString();
        ValidatableResponse response = orderClient.create(new Order(), bearerToken);
        checkResponse(response, HttpStatus.SC_BAD_REQUEST, false, "Ingredient ids must be provided");
    }

    @Epic(value = "Заказ")
    @Feature(value = "Создание заказа")
    @Test
    @DisplayName("Создание заказа c ингредиентами и авторизацией")
    @Description("Создание заказа с валидным списком ингредиентов и с авторизацией")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderValidIngredientListWithAuthorizationTest() {
        String bearerToken = client.register(user).extract().path("accessToken").toString();
        ValidatableResponse response = orderClient.create(new Order(hashes.subList(0, 3)), bearerToken); // создает заказ, используя 3 случайных ингредиента
        checkResponse(response, HttpStatus.SC_OK, true);
        checkRespBodyItemsListIsNotNull(response, List.of("name", "order.number"));
    }

    @Epic(value = "Заказ")
    @Feature(value = "Создание заказа")
    @Test
    @DisplayName("Создание заказа c неверным хэш-кодом ингредиента")
    @Description("Попытка создания заказа с неверным хэш-кодом ингредиента")
    @Severity(SeverityLevel.CRITICAL)
    public void creatingOrderWithInvalidIngredientHashTest() {
        String bearerToken = client.register(user).extract().path("accessToken").toString();
        ValidatableResponse response = orderClient.create(new Order(List.of("0")), bearerToken);
        checkRespStatusCode(response, HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void deleteData() {
        delete(user);
    }

}