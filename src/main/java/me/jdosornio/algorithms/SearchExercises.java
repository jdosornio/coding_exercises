package me.jdosornio.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

@FunctionalInterface
interface InOrderWalker {
    void visit(int element, int depth);
}

enum TrackType {
    START, END
}

class Track implements Comparable<Track> {
    TrackType type;
    int position;

    public Track(int position, TrackType type) {
        this.position = position;
        this.type = type;
    }

    @Override
    public int compareTo(Track o) {
        int comparation = this.position - o.position;

        if (comparation == 0) {
            return this.type.ordinal() - o.type.ordinal();
        }

        return comparation;
    }
}

public class SearchExercises {

    // https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
    public static void whatFlavors(List<Integer> cost, int money) {
        List<List<Integer>> costWithIndex = new ArrayList<>();

        for (int i = 0; i < cost.size(); i++) {
            costWithIndex.add(Arrays.asList(cost.get(i), i + 1));
        }
        costWithIndex.sort((a, b) -> a.get(0) - b.get(0));
        for (int i = 0; i < costWithIndex.size() - 1; i++) {
            int idx = Collections.binarySearch(
                    costWithIndex.subList(i + 1, costWithIndex.size()),
                    Arrays.asList(money - costWithIndex.get(i).get(0)),
                    (a, b) -> a.get(0) - b.get(0));

            if (idx >= 0) {
                int i1 = costWithIndex.get(i).get(1);
                int i2 = costWithIndex.get(idx + i + 1).get(1);
                System.out.printf("%d %d\n", Math.min(i1, i2), Math.max(i1, i2));
                return;
            }
        }
    }

    private static void traverseInOrder(List<List<Integer>> treeIdx,
            InOrderWalker walker) {
        int depth = 1;
        int currRoot = 1;
        Stack<List<Integer>> roots = new Stack<>();

        while (currRoot != -1 || !roots.isEmpty()) {
            while (currRoot != -1) {
                roots.push(Arrays.asList(currRoot, depth));
                // left
                currRoot = treeIdx.get(currRoot - 1).get(0);
                depth++;
            }
            if (!roots.isEmpty()) {
                List<Integer> root = roots.pop();
                walker.visit(root.get(0), root.get(1));
                // right
                currRoot = treeIdx.get(root.get(0) - 1).get(1);
                depth = root.get(1) + 1;
            }
        }
    }

    // https://www.hackerrank.com/challenges/swap-nodes-algo/problem
    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<List<Integer>> traversals = new ArrayList<>();
        Map<Integer, List<Integer>> depths = new HashMap<>();

        traverseInOrder(indexes, (element, depth) -> {
            List<Integer> nodes = depths.get(depth);
            if (nodes != null) {
                nodes.add(element);
            } else {
                depths.put(depth, new ArrayList<>(Arrays.asList(element)));
            }
        });

        for (int q : queries) {
            int k = q;
            List<Integer> nodes = depths.get(k);
            while (nodes != null) {
                for (int node : nodes) {
                    List<Integer> children = indexes.get(node - 1);
                    int temp = children.set(1, children.get(0));
                    children.set(0, temp);
                }
                k += q;
                nodes = depths.get(k);
            }
            List<Integer> currentTraversal = new ArrayList<>();
            traverseInOrder(indexes, (element, depth) -> {
                currentTraversal.add(element);
            });
            traversals.add(currentTraversal);
        }

        return traversals;
    }

    // https://www.hackerrank.com/challenges/gridland-metro/problem
    // Return type was wrong
    public static long gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
        Map<Integer, PriorityQueue<Track>> rowTracks = new HashMap<>();
        long total = (long) n * (long) m;

        // Go over all k instructions
        for (List<Integer> t : track) {
            PriorityQueue<Track> tracks = rowTracks.get(t.get(0));

            if (tracks == null) {
                tracks = new PriorityQueue<>();
                rowTracks.put(t.get(0), tracks);
            }
            tracks.add(new Track(t.get(1), TrackType.START));
            tracks.add(new Track(t.get(2), TrackType.END));
        }

        // Go over all rows populated with tracks
        for (PriorityQueue<Track> row : rowTracks.values()) {
            // Get all the tracks and remove them from total
            long rowTotal = 0;
            Deque<Track> currentTracks = new ArrayDeque<>();

            while (!row.isEmpty()) {
                Track nextTrack = row.poll();

                if (nextTrack.type == TrackType.START) {
                    currentTracks.addFirst(nextTrack);
                } else {
                    Track poppedTrack = currentTracks.pollFirst();
                    if (currentTracks.isEmpty()) {
                        // Add to row total
                        long trackSpan = nextTrack.position - poppedTrack.position + 1;

                        rowTotal += trackSpan;
                    }
                }
            }
            total -= rowTotal;
        }

        return total;
    }
}
