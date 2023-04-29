package model.pojo;

import java.util.List;

/**
 * @author  smirnov sergey
 * @since   29.04.2023
 */
public class Order {

    private List<String> ingredients;

    public Order() {
        this.ingredients = List.of();
    }

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}