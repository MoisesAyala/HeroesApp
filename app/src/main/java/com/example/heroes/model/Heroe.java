package com.example.heroes.model;


import androidx.appcompat.app.AppCompatActivity;

public class Heroe {
    private String name;
    private Url image;
    private int id;
    private Fullname biography;
    private Appearance appearance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public Url getImage() {
        return image;
    }

    public void setImage(Url image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fullname getBiography() {
        return biography;
    }

    public void setBiography(Fullname biography) {
        this.biography = biography;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }
}