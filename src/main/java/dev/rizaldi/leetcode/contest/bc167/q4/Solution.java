package dev.rizaldi.leetcode.contest.bc167.q4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Verdict: Wrong Answer
 * Refer to {@link dev.rizaldi.leetcode.problem.p3710.Solution} for Accepted solution
 */
public class Solution {
    public int maxPartitionFactor(int[][] points) {
        final List<Point> pointList = Arrays.stream(points)
                .map(p -> new Point(p[0], p[1]))
                .toList();

        final List<PointPair> pairs = new ArrayList<>();
        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i + 1; j < pointList.size(); j++) {
                final Point point1 = pointList.get(i);
                final Point point2 = pointList.get(j);
                final PointPair pair = new PointPair(point1, point2);
                pairs.add(pair);
            }
        }

        final Comparator<PointPair> orderByManhattanDistanceDesc = Comparator
                .comparingLong(PointPair::getManhattanDistance);
        final PriorityQueue<PointPair> queue = new PriorityQueue<>(orderByManhattanDistanceDesc);
        queue.addAll(pairs);

        final var opposites = new HashMap<Point, Set<Point>>();

        final PointPair closestPair = queue.remove();
        opposites.computeIfAbsent(closestPair.point1, (k) -> new HashSet<>()).add(closestPair.point2);
        opposites.computeIfAbsent(closestPair.point2, (k) -> new HashSet<>()).add(closestPair.point1);

        while (!queue.isEmpty()) {
            final PointPair pair = queue.remove();
            for (final Point point : Arrays.asList(pair.point1, pair.point2)) {
            }
        }

        return 0;
    }
}

class PointPair {
    public final Point point1;
    public final Point point2;

    public PointPair(final Point point1, final Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public long getManhattanDistance() {
        final long dx = Math.abs(point1.x - point2.x);
        final long dy = Math.abs(point1.y - point2.y);
        return dx + dy;
    }
}

class Point {
    public final long x;
    public final long y;

    public Point(final long x, final long y) {
        this.x = x;
        this.y = y;
    }

    public long getManhattanDistance(final Point o) {
        final long dx = Math.abs(x - o.x);
        final long dy = Math.abs(y - o.y);
        return dx + dy;
    }
}

class DisjointSet<V> {
    public final Map<V, V> parents = new HashMap<>();

    public V find(final V item) {
        parents.putIfAbsent(item, item);
        final V parent = parents.getOrDefault(item, item);

        if (parent != item) return find(parent);
        return parent;
    }

    public void union(final V item1, final V item2) {
        final V parent1 = find(item1);
        final V parent2 = find(item2);

        if (parent1 != parent2) parents.put(parent2, parent1);
    }
}
