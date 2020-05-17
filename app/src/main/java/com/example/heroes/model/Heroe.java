package com.example.heroes.model;


public class Heroe {
    private String name;
    private Url image;
    private int id;


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
}