package com.example;

public class Pedido {
    private String name;
    private double value;

    public Pedido(String name, double d) {
        this.name = name;
        this.value = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
