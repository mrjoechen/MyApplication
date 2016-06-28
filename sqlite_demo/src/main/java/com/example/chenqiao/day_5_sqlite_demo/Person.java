package com.example.chenqiao.day_5_sqlite_demo;

/**
 * Created by chenqiao on 2016/2/26.
 */
public class Person {

    private Integer id;
    private String name;
    private String gender;
    public Person(){
        super();
    }
    public Person(Integer id,String name){
        this.id=id;
        this.name=name;
    }

    public Person(String name, Integer id, String gender) {
        this.name = name;
        this.id = id;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
