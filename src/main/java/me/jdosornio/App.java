package me.jdosornio;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Runnable r = () -> System.out.println("Running in runnable");

        r.run();

        System.out.println("Running at end");

        List<Integer> numbers = Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99, 100);

        numbers.forEach(number -> System.out.print(number + " "));
        numbers.forEach(System.out::println);

        System.out.println(numbers.stream().filter(num -> num % 2 == 0)
                .mapToInt(num -> num * 2)
                .sum());

    }
}
