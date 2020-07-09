package com.example.Exam;

import java.io.Serializable;

public class Exam implements Serializable {
    private int made;
    private int maModule;
    private String ngayThi;
    private int cauDung;
    private int cauSai;
    private int cauChuaLam;
    private double diemSo;
    private int tgLam;
    private int passed = -1; //-1: Chưa thi | 1: Đỗ | 0: Trượt

    public Exam() {
    }

    public Exam(int made, int maModule, String ngayThi, int cauDung, int cauSai, int cauChuaLam, double diemSo, int tgLam, int passed) {
        this.made = made;
        this.maModule = maModule;
        this.ngayThi = ngayThi;
        this.cauDung = cauDung;
        this.cauSai = cauSai;
        this.cauChuaLam = cauChuaLam;
        this.diemSo = diemSo;
        this.tgLam = tgLam;
        this.passed = passed;
    }

    public int getMade() {
        return made;
    }

    public int getMaModule() {
        return maModule;
    }

    public void setMaModule(int maModule) {
        this.maModule = maModule;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getCauDung() {
        return cauDung;
    }

    public void setCauDung(int cauDung) {
        this.cauDung = cauDung;
    }

    public int getCauSai() {
        return cauSai;
    }

    public void setCauSai(int cauSai) {
        this.cauSai = cauSai;
    }

    public int getCauChuaLam() {
        return cauChuaLam;
    }

    public void setCauChuaLam(int cauChuaLam) {
        this.cauChuaLam = cauChuaLam;
    }

    public double getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(double diemSo) {
        this.diemSo = diemSo;
    }

    public int getTgLam() {
        return tgLam;
    }

    public void setTgLam(int tgLam) {
        this.tgLam = tgLam;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }
}
