package com.ltd.tandung.amazon_shopping.model;

/**
 * Created by letandung on 05/10/2017.
 */

public class Giohang {
    public int idsp;
    public String Tensp;
    public int Giasp;
    public String Hinhanhsp;
    public int soluongsp;

    public Giohang(int idsp, String tensp, int giasp, String hinhanhsp, int soluongsp) {
        this.idsp = idsp;
        Tensp = tensp;
        Giasp = giasp;
        Hinhanhsp = hinhanhsp;
        this.soluongsp = soluongsp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public int getGiasp() {
        return Giasp;
    }

    public void setGiasp(int giasp) {
        Giasp = giasp;
    }

    public String getHinhanhsp() {
        return Hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        Hinhanhsp = hinhanhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
