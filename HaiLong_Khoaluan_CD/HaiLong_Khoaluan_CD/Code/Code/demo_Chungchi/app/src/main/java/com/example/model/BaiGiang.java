package com.example.model;

import java.io.Serializable;

public class BaiGiang implements Serializable {
    private int maBaiGiang;
    private String tenBaiGiang;
    private String anhBaiGiang;
    private String fileName;

    public BaiGiang() {

    }

    public BaiGiang(int maBaiGiang, String tenBaiGiang, String anhBaiGiang, String fileName) {
        this.maBaiGiang = maBaiGiang;
        this.tenBaiGiang = tenBaiGiang;
        this.anhBaiGiang = anhBaiGiang;
        this.fileName = fileName;
    }

    public int getMaBaiGiang() {
        return maBaiGiang;
    }

    public String getTenBaiGiang() {
        return tenBaiGiang;
    }

    public void setTenBaiGiang(String tenBaiGiang) {
        this.tenBaiGiang = tenBaiGiang;
    }

    public String getAnhBaiGiang() {
        return anhBaiGiang;
    }

    public void setAnhBaiGiang(String anhBaiGiang) {
        this.anhBaiGiang = anhBaiGiang;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
