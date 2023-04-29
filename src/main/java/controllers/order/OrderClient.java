package controllers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.pojo.Order;

import static api.Endpoints.CREATE_ORDER;
import static api.Specification.setSpec;

/**
 * Служебный класс для взаимодействия с сущностью Order
 *
 * @author  smirnov sergey
 * @since   29.04.2023
 */
public class OrderClient {

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return setSpec().body(order).post(CREATE_ORDER.getEndpoint()).then();
    }

}