package steps;

import controllers.user.UserClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.pojo.User;
import org.apache.http.HttpStatus;

import java.util.List;

import static model.pojo.UserCreds.credsFrom;
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

    @Step("Статус код ответа: {respStatusCode}")
    public static void checkRespStatusCode(ValidatableResponse response, int respStatusCode) {
        assertEquals("Статус код неверный", respStatusCode, response.extract().statusCode());
    }

    @Step("Статус ответа: {respStatusMessage}")
    public static void checkRespStatus(ValidatableResponse response, Boolean respStatusMessage) {
        response.assertThat().body("success", equalTo(respStatusMessage));
    }

    @Step("Проверка ответа. Значение поля {field} корректно")
    public static void checkRespBodyElement(ValidatableResponse response, String field, String value) {
        response.assertThat().body(field, equalTo(value));
    }

    // проверка списка полей ответа на пустоту
    @Step("Проверка полей ответа")
    public static void checkRespBodyItemsListIsNotNull(ValidatableResponse response, List<String> fields) {
        fields.forEach(x -> BaseSteps.checkRespBodyElementIsNotNull(response, x));
    }

    @Step("Поле {field} не пустое")
    public static void checkRespBodyElementIsNotNull(ValidatableResponse response, String field) {
        response.assertThat().body(field, notNullValue());
    }

    @Step("Сообщение ответа: {respMessage}")
    public static void checkRespBodyMessage(ValidatableResponse response, String respMessage) {
        response.assertThat().body("message", equalTo(respMessage));
    }

    @Step("Проверка ответа")
    public static void checkResponse(ValidatableResponse response, int respStatusCode, Boolean respStatusMessage, String respMessage) {
        checkRespStatusCode(response, respStatusCode);
        checkRespStatus(response, respStatusMessage);
        checkRespBodyMessage(response, respMessage);
    }

    @Step("Проверка ответа")
    public static void checkResponse(ValidatableResponse response, int respStatusCode, Boolean respStatusMessage) {
        checkRespStatusCode(response, respStatusCode);
        checkRespStatus(response, respStatusMessage);
    }

    @Step("Удаление тестовых данных")
    public static void delete(User user) {
        UserClient client = new UserClient();
        ValidatableResponse response = client.login(credsFrom(user));

        if(response.extract().statusCode() == HttpStatus.SC_OK)
            client.delete(response.extract().path("accessToken").toString());
    }

}