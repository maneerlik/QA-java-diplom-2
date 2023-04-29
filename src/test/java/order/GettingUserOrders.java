package order;

import controllers.order.OrderClient;
import controllers.user.UserClient;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.pojo.User;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.RandomUser.randomValidUser;
import static steps.BaseSteps.*;

/**
 * Тест получение заказов пользователя
 *
 * @author  smirnov sergey
 * @since   28.04.2023
 */
public class GettingUserOrders {

    private User user;
    private UserClient client;
    private OrderClient orderClient;

    @Before
    public void setup() {
        user = randomValidUser();
        client = new UserClient();
        orderClient = new OrderClient();
    }

    @Epic(value = "Заказ")
    @Feature(value = "Получение заказов пользователя")
    @Test
    @DisplayName("Получение заказов без авторизации")
    @Description("Получение заказов пользователя без авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void gettingUserOrdersWithoutAutorizationTest() {
        ValidatableResponse response = orderClient.getOrders();
        checkResponse(response, HttpStatus.SC_UNAUTHORIZED, false, "You should be authorised");
    }

    @Epic(value = "Заказ")
    @Feature(value = "Получение заказов пользователя")
    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void gettingUserOrdersWithAuthorizedTest() {
        String bearerToken = client.register(user).extract().path("accessToken").toString();
        ValidatableResponse response = orderClient.getOrders(bearerToken);
        checkResponse(response, HttpStatus.SC_OK, true);
        checkRespBodyElementIsNotNull(response, "orders");
    }

    @After
    public void deleteData() {
        delete(user);
    }

}