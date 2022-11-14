package com.app.foodorder.Activity.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private String pic;
    private String mota;
    private double gia;
    private int SoLuong;


    public FoodDomain(String title, String pic, String mota, double gia) {
        this.title = title;
        this.pic = pic;
        this.mota = mota;
        this.gia = gia;
    }

    public FoodDomain(String title, String pic, String mota, double gia, int soLuong) {
        this.title = title;
        this.pic = pic;
        this.mota = mota;
        this.gia = gia;
        SoLuong = soLuong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(int  gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
