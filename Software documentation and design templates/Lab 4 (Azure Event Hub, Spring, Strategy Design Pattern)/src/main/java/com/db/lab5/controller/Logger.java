package com.db.lab5.controller;

import com.db.lab5.controller.Strategy;

public class Logger implements Strategy {

    @Override
    public void print(String log) {
        System.out.println(log);
    }
}
