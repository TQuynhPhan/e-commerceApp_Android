package com.example.ecommerceapp.model;

public class GioHang {
    private int idSP;
    private String tenSP;
    private int giaSP;
    private String hinhAnhSP;
    private int soLuongSP;
    private boolean checkChonMua;

    public GioHang(int idSP, String tenSP, int giaSP, String hinhAnhSP, int soLuongSP, boolean checkChonMua) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinhAnhSP = hinhAnhSP;
        this.soLuongSP = soLuongSP;
        this.checkChonMua = checkChonMua;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public boolean isCheckChonMua() {
        return checkChonMua;
    }

    public void setCheckChonMua(boolean checkChonMua) {
        this.checkChonMua = checkChonMua;
    }
}
