package com.db.lab5.controller;

public class Logger implements Strategy {

    @Override
    public void print(String log) {
        System.out.println(log);
    }
}
