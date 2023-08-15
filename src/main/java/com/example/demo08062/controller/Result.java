package com.example.demo08062.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String msg;

    public static Result ok(String msg){
        return new Result(true, msg);
    }
    public static Result fail(String msg){
        return new Result(false, msg);
    }
}
