package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * Класс, реализующий универсальные базовые шаги
 *
 * @author  smirnov sergey
 * @since   24.04.2023
 */
public class BaseSteps {

    @Step("Статус код ответа: {status}")
    public static void checkRespStatusCode(ValidatableResponse response, int status) {
        assertEquals("Статус код неверный", status, response.extract().statusCode());
    }

    @Step("Статус ответа: {message}")
    public static void checkRespStatus(ValidatableResponse response, Boolean message) {
        response.assertThat().body("success", equalTo(message));
    }

    @Step("Проверка ответа. Значение поля {field} корректно")
    public static void checkRespBodyElement(ValidatableResponse response, String field, String value) {
        response.assertThat().body(field, equalTo(value));
    }

    @Step("Проверка ответа. Поле {field} не пустое")
    public static void checkRespBodyElementIsNotNull(ValidatableResponse response, String field) {
        response.assertThat().body(field, notNullValue());
    }

    @Step("Сообщение ответа: {message}")
    public static void checkRespBodyMessage(ValidatableResponse response, String message) {
        response.assertThat().body("message", equalTo(message));
    }

}