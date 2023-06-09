package controllers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.pojo.Order;

import static api.Endpoints.CREATE_ORDER;
import static api.Endpoints.GET_ORDERS;
import static api.Specification.setSpec;

/**
 * Служебный класс для взаимодействия с сущностью Order
 *
 * @author  smirnov sergey
 * @since   29.04.2023
 */
public class OrderClient {

    @Step("Создание заказа без авторизации")
    public ValidatableResponse create(Order order) {
        return setSpec().body(order).post(CREATE_ORDER.getEndpoint()).then();
    }

    @Step("Создание заказа с авторизацией")
    public ValidatableResponse create(Order order, String bearerToken) {
        return setSpec().headers("Authorization", bearerToken).body(order).post(CREATE_ORDER.getEndpoint()).then();
    }

    @Step("Получение заказов без авторизации")
    public ValidatableResponse getOrders() {
        return setSpec().get(GET_ORDERS.getEndpoint()).then();
    }

    @Step("Получение заказов с авторизацией")
    public ValidatableResponse getOrders(String bearerToken) {
        return setSpec().headers("Authorization", bearerToken).get(GET_ORDERS.getEndpoint()).then();
    }

}