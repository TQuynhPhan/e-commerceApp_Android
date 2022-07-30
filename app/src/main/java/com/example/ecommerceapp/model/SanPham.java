package com.example.ecommerceapp.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id;
    private String ten;
    private int gia;
    private String hinhAnh;
    private String moTa;
    private int idLoaiSP;

    public SanPham(int id, String ten, int gia, String hinhAnh, String moTa, int idLoaiSP) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.idLoaiSP = idLoaiSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }
}
