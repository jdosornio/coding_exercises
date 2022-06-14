package me.jdosornio.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphTheory {
    // https://www.hackerrank.com/challenges/components-in-graph/problem
    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        int maxSize = Integer.MIN_VALUE;
        int minSize = Integer.MAX_VALUE;

        for (int i = 0; i < gb.size(); i++) {
            List<Integer> edge = gb.get(i);
            List<Integer> neighbors = edges.get(edge.get(0));
            if (neighbors == null) {
                edges.put(edge.get(0), new ArrayList<>(Arrays.asList(edge.get(1))));
            } else {
                neighbors.add(edge.get(1));
            }
            neighbors = edges.get(edge.get(1));
            if (neighbors == null) {
                edges.put(edge.get(1), new ArrayList<>(Arrays.asList(edge.get(0))));
            } else {
                neighbors.add(edge.get(0));
            }
        }

        Set<Integer> visitedNodes = new HashSet<>();
        for (int i = 0; i < gb.size(); i++) {
            List<Integer> edge = gb.get(i);

            if (!visitedNodes.contains(edge.get(0))) {
                // DFS
                Deque<Integer> stack = new ArrayDeque<>();
                int size = 0;

                stack.addFirst(edge.get(0));

                while (!stack.isEmpty()) {
                    int current = stack.pollFirst();
                    if (!visitedNodes.contains(current)) {
                        visitedNodes.add(current);
                        size++;
                        List<Integer> nextNodes = edges.get(current);

                        for (int next : nextNodes) {
                            stack.addFirst(next);
                        }
                    }
                }
                if (size < minSize) {
                    minSize = size;
                }
                if (size > maxSize) {
                    maxSize = size;
                }
            }
        }

        return Arrays.asList(minSize, maxSize);
    }
}