package me.jdosornio.structures.heaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/qheap1/problem
public class Exercise1 {
    private static List<Integer> qheap(List<List<Integer>> queries) {
        List<Integer> minimums = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (List<Integer> query : queries) {
            int operation = query.get(0);

            switch (operation) {
                case 1:
                    heap.add(query.get(1));
                    break;
                case 2:
                    heap.remove(query.get(1));
                    break;
                case 3:
                    minimums.add(heap.peek());
                    break;
            }
        }

        return minimums;
    }

    public static void main(String[] args) {
        List<List<Integer>> queries = new ArrayList<>();
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numQueries = Integer.parseInt(stdIn.readLine());

            for (int l = 0; l < numQueries; l++) {
                List<Integer> query = Arrays.stream(stdIn.readLine().split("\\s"))
                        .map(s -> Integer.parseInt(s)).collect(toList());

                queries.add(query);
            }
            List<Integer> output = qheap(queries);

            output.forEach(System.out::println);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}