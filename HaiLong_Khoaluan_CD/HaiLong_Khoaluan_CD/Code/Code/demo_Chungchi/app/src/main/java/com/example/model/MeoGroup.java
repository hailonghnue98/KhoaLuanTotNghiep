package com.example.model;

import java.util.ArrayList;

public class MeoGroup {
    private String name;
    private ArrayList<MeoItem> listItem = new ArrayList<>();

    public MeoGroup() {
    }

    public MeoGroup(String name, ArrayList<MeoItem> listItem) {
        this.name = name;
        this.listItem = listItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MeoItem> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<MeoItem> listItem) {
        this.listItem = listItem;
    }
}
