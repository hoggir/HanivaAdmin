package com.example.hanivaadmin.Model;

public class pinjaman_user {

    private String jmlhpinjaman;
    private String jangkawaktu;
    private String jaminan;
    private String status;
    private String key;
    private String iduser;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getJmlhpinjaman() {
        return jmlhpinjaman;
    }

    public void setJmlhpinjaman(String jmlhpinjaman) {
        this.jmlhpinjaman = jmlhpinjaman;
    }

    public String getJangkawaktu() {
        return jangkawaktu;
    }

    public void setJangkawaktu(String jangkawaktu) {
        this.jangkawaktu = jangkawaktu;
    }

    public String getJaminan() {
        return jaminan;
    }

    public void setJaminan(String jaminan) {
        this.jaminan = jaminan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public pinjaman_user(){

    }

    public pinjaman_user(String jmlhpinjaman, String jangkawaktu, String jaminan,
                         String status, String iduser){
        this.jmlhpinjaman = jmlhpinjaman;
        this.jangkawaktu = jangkawaktu;
        this.jaminan = jaminan;
        this.status = status;
        this.iduser = iduser;
    }
}
