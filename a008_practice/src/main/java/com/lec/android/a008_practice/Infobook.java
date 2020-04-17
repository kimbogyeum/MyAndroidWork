package com.lec.android.a008_practice;

import java.io.Serializable;

public class Infobook implements Serializable {

    String name;
    String age;
    String email;

    public Infobook() { }

    public Infobook(String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
