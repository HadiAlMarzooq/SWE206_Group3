package com.example.notthefinalproject;


import java.math.BigInteger;

public class Student {
    String name, major;
    Double id;

    Student(String name , Double id, String major){
        this.name = name;
        this.id = id;
        this.major = major;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
