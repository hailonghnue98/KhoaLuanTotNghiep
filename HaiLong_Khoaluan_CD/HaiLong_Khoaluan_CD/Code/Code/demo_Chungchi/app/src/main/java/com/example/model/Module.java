package com.example.model;

import java.io.Serializable;

public class Module implements Serializable {
    private String name;
    private String title;
    private int image;

    public Module() {
    }

    public Module(String name, String title, int image) {
        this.name = name;
        this.title = title;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
