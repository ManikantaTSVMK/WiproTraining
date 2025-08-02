package com.example.java8features;

import java.util.function.Function;

// Here it takes one input(T) and return a result(R) -- R apply(T t)
public class FunctionalExample {

    public static void main(String[] args) {

        Function<String, Integer> toLength = s->s.length();

        System.out.println("Length of value is : " + toLength.apply("Mani"));
    }
}