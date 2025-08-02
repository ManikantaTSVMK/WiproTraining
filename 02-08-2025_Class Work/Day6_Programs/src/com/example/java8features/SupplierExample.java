package com.example.java8features;

import java.util.function.Supplier;

// It will produce the result without input -- T get()
public class SupplierExample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Supplier<Double> randomNumbers = () -> Math.random();
        System.out.println("Random Values are : " + randomNumbers.get());
    }
}