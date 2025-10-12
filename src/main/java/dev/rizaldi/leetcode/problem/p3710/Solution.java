package dev.rizaldi.leetcode.problem.p3710;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>3710. Maximum Partition Factor</p>
 * <p>Difficulty: Hard</p>
 * <p>Verdict: Accepted</p>
 * <p>Link: <a href="https://leetcode.com/problems/maximum-partition-factor/description/">LeetCode</a></p>
 */
public class Solution {
    public int maxPartitionFactor(int[][] points) {
        final List<Point> pointList = Arrays.stream(points)
                .map(p -> new Point(p[0], p[1]))
                .toList();

        final List<Point[]> pairs = new ArrayList<>();
        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i + 1; j < pointList.size(); j++) {
                final Point point1 = pointList.get(i);
                final Point point2 = pointList.get(j);
                pairs.add(new Point[]{point1, point2});
            }
        }
        pairs.sort(Point.ORDER_BY_DISTANCE);

        final DisjointSet<Point> friends = new DisjointSet<>();
        final Map<Point, Point> enemies = new HashMap<>();

        for (final Point[] pair : pairs) {
            if (enemies.containsKey(pair[0]) && enemies.containsKey(pair[1])) {
                final Point enemy0 = friends.find(enemies.get(pair[0]));
                final Point enemy1 = friends.find(enemies.get(pair[1]));

                if (enemy0 == enemy1) {
                    return pair[0].getManhattanDistance(pair[1]);
                } else {
                    friends.union(pair[0], enemy1);
                    friends.union(pair[1], enemy0);
                }
            } else if (enemies.containsKey(pair[0])) {
                final Point enemy0 = enemies.get(pair[0]);
                friends.union(pair[1], enemy0);
                enemies.put(pair[1], pair[0]);
            } else if (enemies.containsKey(pair[1])) {
                final Point enemy1 = enemies.get(pair[1]);
                friends.union(pair[0], enemy1);
                enemies.put(pair[0], pair[1]);
            } else {
                enemies.put(pair[0], pair[1]);
                enemies.put(pair[1], pair[0]);
            }
        }

        return 0;
    }
}

class Point {
    public static final Comparator<Point[]> ORDER_BY_DISTANCE = Comparator
            .comparingInt((p) -> p[0].getManhattanDistance(p[1]));

    public final int x;
    public final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getManhattanDistance(final Point o) {
        final int dx = Math.abs(x - o.x);
        final int dy = Math.abs(y - o.y);
        return dx + dy;
    }
}

class DisjointSet<V> {
    public final Map<V, V> parents = new HashMap<>();

    public V find(final V item) {
        final V parent = parents.getOrDefault(item, item);
        if (parent != item) return find(parent);
        return parent;
    }

    public void union(final V item1, final V item2) {
        final V parent1 = find(item1);
        final V parent2 = find(item2);
        parents.put(parent2, parent1);
    }
}
