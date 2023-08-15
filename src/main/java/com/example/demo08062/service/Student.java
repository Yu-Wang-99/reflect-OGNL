package com.example.demo08062.service;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class Student {
    //基本数据类型
    public byte aByte;
    private short aShort;
    public int anInt;
    private long  aLong;
    public float aFloat;
    private double aDouble;
    public char aChar;
    private boolean aBoolean;

    //引用数据类型
    private String aString;

    //静态变量
    public static int staticInt;
    private static String staticString;

    //构造函数
    public Student() {
    }
    public Student(byte aByte, short aShort, int anInt, long aLong, float aFloat, double aDouble, char aChar, boolean aBoolean, String aString) {
        this.aByte = aByte;
        this.aShort = aShort;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aChar = aChar;
        this.aBoolean = aBoolean;
        this.aString = aString;
    }

    //get set方法
    public byte getByteField() {
        return aByte;
    }

    public void setByteField(byte byteField) {
        this.aByte = byteField;
    }

    public short getaShort() {
        return aShort;
    }

    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public static int getStaticInt() {
        return staticInt;
    }

    public static void setStaticInt(int staticInt) {
        Student.staticInt = staticInt;
    }

    public static String getStaticString() {
        return staticString;
    }

    public static void setStaticString(String staticString) {
        Student.staticString = staticString;
    }

    @Override
    public String toString() {
        return "Student{" +
                "aByte=" + aByte +
                ", aShort=" + aShort +
                ", anInt=" + anInt +
                ", aLong=" + aLong +
                ", aFloat=" + aFloat +
                ", aDouble=" + aDouble +
                ", aChar=" + aChar +
                ", aBoolean=" + aBoolean +
                ", aString=" + aString +
                ", staticInt=" + staticInt+
                ", staticString=" + staticString+
                '}';
    }
}
