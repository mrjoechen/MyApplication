package com.example.chenqiao.day_8_listview_homework;

import java.util.IdentityHashMap;

/**
 * Created by chenqiao on 2016/3/2.
 */
public class Star {
    private int id;
    private String name;
    private String nationality;

    public Star(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
