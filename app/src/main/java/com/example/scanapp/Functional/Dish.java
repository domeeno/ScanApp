package com.example.scanapp.Functional;

public class Dish {
    private String dishName;
    private String dishPrice;
    private String dishIngredients;
//    private String dishImage;
    private int dishCount = 1;

    public Dish(String dishName, String dishPrice) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
//        this.dishImage = dishImage;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishPrice() {
        return dishPrice;
    }

//    public String getDishImage() {
//        return dishImage;
//    }

    public int getDishCount() {
        return dishCount;
    }

    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishIngredients(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }
}