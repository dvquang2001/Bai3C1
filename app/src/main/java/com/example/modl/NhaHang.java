package com.example.modl;

public class NhaHang {
    private int hinh;
    private String ten;
    private String sdt;

    public NhaHang() {
    }

    public NhaHang(int hinh, String ten, String sdt) {
        this.hinh = hinh;
        this.ten = ten;
        this.sdt = sdt;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
