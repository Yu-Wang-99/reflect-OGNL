package com.example.demo08062.service;


import org.springframework.stereotype.Component;

@Component
public class Address {
    public String address;
    private int age;
    static private double score;
    public boolean isSold;

    public Address() {
    }

    public Address(String address, int age, boolean isSold) {
        this.address = address;
        this.age = age;
        this.isSold = isSold;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static double getScore() {
        return score;
    }

    public static void setScore(double score) {
        Address.score = score;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", age=" + age +
                ", isSold=" + isSold +
                ", score=" + score +
                '}';
    }
}
