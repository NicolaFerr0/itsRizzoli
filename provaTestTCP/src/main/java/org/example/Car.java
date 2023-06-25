package org.example;

public class Car {

    private int id;

    private String marca;
    private double cost;
    private double qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

        public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Car(int id, String marca, double cost, double qty) {
        setId(id);
        setMarca(marca);
        setCost(cost);
        setQty(qty);
    }

    public String toString() {
        return "God{" +
                "id='" + id + '\'' +
                ", marca='" + marca + '\'' +
                ", cost='" + cost + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
