package com.example.scanapp.Functional;

public class Category {
    private String categoryName; //Name
//    private String categoryImage;

    public Category(String categoryName) {
        this.categoryName = categoryName;
//        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

//    public String getCategoryImage(){
//        return categoryImage;
//    }
}
