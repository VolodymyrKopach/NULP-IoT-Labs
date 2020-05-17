package com.db.lab5.controller;

class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeLogStrategy(String log) {
        strategy.print(log);
    }
}
