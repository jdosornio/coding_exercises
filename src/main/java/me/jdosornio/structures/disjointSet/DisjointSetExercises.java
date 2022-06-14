package me.jdosornio.structures.disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DisjointSet {
    int[] parents;
    int[] sizes;

    public DisjointSet(int n) {
        parents = new int[n + 1];
        sizes = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    private int find(int p) {
        int root = p;

        while (parents[root] != root) {
            parents[root] = parents[parents[root]];
            root = parents[root];
        }

        return root;
    }

    public int getSize(int p) {
        int pRoot = find(p);

        return sizes[pRoot];
    }

    public void merge(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (sizes[pRoot] < sizes[qRoot]) {
            // p set size less than q set size
            parents[pRoot] = qRoot;
            sizes[qRoot] += sizes[pRoot];
        } else {
            parents[qRoot] = pRoot;
            sizes[pRoot] += sizes[qRoot];
        }
    }
}

public class DisjointSetExercises {
    // https://www.hackerrank.com/challenges/merging-communities/problem
    public static void mergingCommunities() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split("\\s");
        int n = Integer.parseInt(firstLine[0]);
        DisjointSet set = new DisjointSet(n);

        reader.lines().forEach(line -> {
            String[] command = line.split("\\s");

            if (command[0].equals("M")) {
                int person1 = Integer.parseInt(command[1]);
                int person2 = Integer.parseInt(command[2]);

                set.merge(person1, person2);
            } else if (command[0].equals("Q")) {
                int person = Integer.parseInt(command[1]);
                System.out.println(set.getSize(person));
            }
        });
    }
}
