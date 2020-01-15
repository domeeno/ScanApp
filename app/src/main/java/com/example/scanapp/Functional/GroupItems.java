package com.example.scanapp.Functional;

public class GroupItems {
    private String img; //Image Data
    private String groupName; //Name

    public GroupItems(String name, String img)
    {
        this.img = img;
        this.groupName = name;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return groupName;
    }
}
