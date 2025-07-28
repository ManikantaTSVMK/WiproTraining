package com.wipro;

public class Conversion {

	public static void main(String[] args) {
        byte b = 100;
        short s = b;  // Implicit conversion (widening)

        System.out.println("Byte value: " + b);
        System.out.println("Converted Short value: " + s);
    }

}
