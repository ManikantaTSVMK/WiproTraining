package com.example.java8features;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {

    public static void main(String[] args) {
    	
    	// It gives a boolean valued function with one argument i.e. boolean test(T t);
    	Predicate<String> isLong = s->s.length() > 4 ;//Taking an input in a String and returning boolean value only
    	System.out.println(isLong.test("Hello")); // True r false
    	
    	System.out.println(isLong.test("Hello String Value"));
    	System.out.println(isLong.test("He"));
        List<String> words = List.of("Java", " CSharp", "Python", "C++", "Encapsulation", "Abstraction");
        Predicate<String> isLong1 = s -> s.length() > 6;
        List<String> longWords = words.stream()
                                      .filter(isLong1)
                                      .collect(Collectors.toList());
        System.out.println("Long words which are greater then 6 letters : " + longWords);
        
    }
}
