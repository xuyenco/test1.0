package com.example.test10.Model;

public class ThiSinhModel {
    public int ID;
    public String HoTen;
    public String SoBaoDanh;
    public double DiemToan;
    public double DiemLy;
    public double DiemHoa;
    public double DiemTb;



    public ThiSinhModel(int ID, String HoTen, String SoBaoDanh, double DiemToan, double DiemLy, double DiemHoa, double Diemtb) {
        this.ID = ID;
        this.HoTen = HoTen;
        this.SoBaoDanh = SoBaoDanh;
        this.DiemToan = DiemToan;
        this.DiemLy = DiemLy;
        this.DiemHoa = DiemHoa;
        this.DiemTb = Diemtb;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSoBaoDanh() {
        return SoBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        SoBaoDanh = soBaoDanh;
    }

    public double getDiemToan() {
        return DiemToan;
    }

    public void setDiemToan(double diemToan) {
        DiemToan = diemToan;
    }

    public double getDiemLy() {
        return DiemLy;
    }

    public void setDiemLy(double diemLy) {
        DiemLy = diemLy;
    }

    public double getDiemHoa() {
        return DiemHoa;
    }

    public void setDiemHoa(double diemHoa) {
        DiemHoa = diemHoa;
    }
    public double getDiemTb() {
        return DiemTb;
    }

    public void setDiemTb(double diemTb) {
        DiemTb = diemTb;
    }
}
