package com.example.heroes.model;

import com.google.gson.annotations.SerializedName;

public class Fullname {
    @SerializedName("full-name")
    private String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
