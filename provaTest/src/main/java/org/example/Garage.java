package org.example;


import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Garage {
    private static Garage istance;
    private List<Car> carsList = new ArrayList();

    private Garage() {
        this.carsList.add(new Car(123, "bmw", 3594.9, 2));
        this.carsList.add(new Car(3634, "audi", 38346.9, 1));
        this.carsList.add(new Car(135, "ferrari", 130000.4, 10));
    }

    public static Garage getInstance() {
        if (istance == null) {
            istance = new Garage();
        }
        return istance;
    }

    public String toJson() {
        Gson gson = new Gson();
        String s = gson.toJson(carsList);

        return s;
    }

    public void sendMoreExpensiveCars(PrintWriter out) {
        double maxPrice = 0.0;
        Car mostExpensiveCar = null;

        for (Car car : carsList) {
            if (car.getCost() > maxPrice) {
                maxPrice = car.getCost();
                mostExpensiveCar = car;
            }
        }

        if (mostExpensiveCar != null) {
            List<Car> responseList = new ArrayList<>();
            responseList.add(mostExpensiveCar);
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(responseList);
            out.println(jsonResponse);
        } else {
            out.println("{\"cars\": []}");
        }
    }

    public void sendAllCarsSorted(PrintWriter out) {
        if (carsList.isEmpty()) {
            out.println("{\"cars\": []}");
        } else {
            List<Car> sortedList = new ArrayList<>(carsList);
            sortedList.sort(Comparator.comparing(Car::getMarca));
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(sortedList);
            out.println(jsonResponse);
        }
    }

}