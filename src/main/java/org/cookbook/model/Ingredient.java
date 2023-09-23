package org.cookbook.model;

public class Ingredient {
    private String name;
    private float amount;
    private String measurement;
    public Ingredient(){}
    public Ingredient (String name, float amount, String measurement) {
        this.name = name;
        this.amount = amount;
        this.measurement = measurement;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
    public String toString() {
        return amount + " " + measurement + " of " + name;
    }
}