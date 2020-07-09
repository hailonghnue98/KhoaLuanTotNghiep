package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupBaiGiang implements Serializable {
    private String name;
    private ArrayList<BaiGiang> listBG = new ArrayList<>();
    private int module;

    public GroupBaiGiang() {

    }

    public GroupBaiGiang(String name, ArrayList<BaiGiang> listBG, int module) {
        this.name = name;
        this.listBG = listBG;
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BaiGiang> getListBG() {
        return listBG;
    }

    public void setListBG(ArrayList<BaiGiang> listBG) {
        this.listBG = listBG;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }
}
