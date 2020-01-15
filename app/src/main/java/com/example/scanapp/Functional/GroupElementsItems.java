package com.example.scanapp.Functional;

public class GroupElementsItems {
    private String img; //Image Data
    private String groupName; //Name of the dish
    private Integer price; //the price of the dish

    public GroupElementsItems(String name, String img, Integer price)
    {
        this.img = img;
        this.groupName = name;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return groupName;
    }

    public Integer getPrice() {
        return price;
    }
}
