package model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * @author  smirnov sergey
 * @since   28.04.2023
 */
public class Ingredient {

    @SerializedName("_id")
    private String id;
    private String name;
    private String type;
    private Integer proteins;
    private Integer fat;
    private Integer carbohydrates;
    private Integer calories;
    private Integer price;
    private String image;
    @SerializedName("image_mobile")
    private String imageMobile;
    @SerializedName("image_large")
    private String imageLarge;
    @SerializedName("__v")
    private Integer v;

    public Ingredient() {
    }

    public Ingredient(String id, String name, String type, Integer proteins, Integer fat, Integer carbohydrates, Integer calories, Integer price, String image, String imageMobile, String imageLarge, Integer v) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.imageMobile = imageMobile;
        this.imageLarge = imageLarge;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageMobile() {
        return imageMobile;
    }

    public void setImageMobile(String imageMobile) {
        this.imageMobile = imageMobile;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}