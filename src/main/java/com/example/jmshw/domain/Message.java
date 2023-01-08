package com.example.jmshw.domain;

public class Message {
    private String name;
    private Condition condition;

    public Message() {}

    public Message(String name, Condition condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return  "Name= " + name + '\n' +
                "Condition= " + condition;
    }
}
