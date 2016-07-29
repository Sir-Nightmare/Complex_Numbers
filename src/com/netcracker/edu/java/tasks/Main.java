package com.netcracker.edu.java.tasks;

import static java.lang.Double.parseDouble;

/**
 * Created by Sir Nightmare on 07/29/16.
 */
public class Main {
    public static void main(String[] args) {
        ComplexNumber num = new ComplexNumberImpl("+4-i");
        System.out.println(num.getRe());
        System.out.println(num.getIm());

    }
}
