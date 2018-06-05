package com.example.mariliaportela.projetofinal.model;

public class FilmeFavorito {

    private String idFilme;
    private String userUID;
    private String title;
    private String poster_path;
    private String overview;
    private String release_date;

    public FilmeFavorito() {
    }

    public FilmeFavorito(String idFilme, String userUID, String title, String poster_path, String overview, String release_date) {
        this.idFilme = idFilme;
        this.userUID = userUID;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "FilmeFavorito{" +
                "idFilme='" + idFilme + '\'' +
                ", userUID='" + userUID + '\'' +
                ", title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
