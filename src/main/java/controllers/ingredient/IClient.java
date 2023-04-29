package controllers.ingredient;

import io.qameta.allure.Step;
import model.pojo.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

import static api.Endpoints.GET_INGREDIENTS;
import static api.Specification.spec;

/**
 * Служебный класс для взаимодействия с сущностью Ingredient
 *
 * @author  smirnov sergey
 * @since   28.04.2023
 */
public class IClient {

    @Step("Получение списка ингредиентов")
    public List<Ingredient> getIngredients() {
        return spec().get(GET_INGREDIENTS.getEndpoint())
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("data", Ingredient.class);
    }

    @Step("Получение списка хэш-кодов ингредиентов")
    public List<String> getHashes() {
        return getIngredients().stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());
    }

}