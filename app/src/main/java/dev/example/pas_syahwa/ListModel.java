package dev.example.pas_syahwa;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListModel extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String nama;
    private String kota;
    private String gambar;
    private String deskripsi;
    private float rating;
    private boolean isFavorite;

    public ListModel() {}

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public ListModel(String kota , String nama, String gambar, String deskripsi, boolean isFavorite, float rating) {
        this.nama = nama;
        this.kota = kota;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.isFavorite = isFavorite;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }
}

