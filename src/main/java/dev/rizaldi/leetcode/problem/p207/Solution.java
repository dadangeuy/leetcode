package dev.rizaldi.leetcode.problem.p207;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * <p>207. Course Schedule</p>
 * <p>Difficulty: Medium</p>
 * <p>Verdict: Accepted</p>
 * <p>Link: <a href="https://leetcode.com/problems/course-schedule/description/">LeetCode</a></p>
 */
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        final Graph<Integer, int[]> nextCourses = new Graph<>();
        final Graph<Integer, int[]> prevCourses = new Graph<>();

        for (final int[] prerequisite : prerequisites) {
            final int course = prerequisite[0];
            final int prevCourse = prerequisite[1];

            nextCourses.add(prevCourse, course, prerequisite);
            prevCourses.add(course, prevCourse, prerequisite);
        }

        final Queue<Integer> availableCourses = new LinkedList<>();
        for (int course = 0; course < numCourses; course++) {
            final boolean isAvailable = prevCourses.get(course).isEmpty();
            if (isAvailable) availableCourses.add(course);
        }

        final List<Integer> finishedCourses = new LinkedList<>();
        while (!availableCourses.isEmpty()) {
            final int course = availableCourses.remove();
            finishedCourses.add(course);

            for (final int nextCourse : nextCourses.get(course)) {
                prevCourses.remove(nextCourse, course);
                final boolean isAvailable = prevCourses.get(nextCourse).isEmpty();
                if (isAvailable) availableCourses.add(nextCourse);
            }
        }

        final boolean isFinishable = finishedCourses.size() == numCourses;
        return isFinishable;
    }
}

class Graph<V, E> {
    private final Map<V, Map<V, E>> edges = new HashMap<>();

    public void add(final V fromVertex, final V intoVertex, final E edge) {
        edges.computeIfAbsent(fromVertex, k -> new HashMap<>()).put(intoVertex, edge);
    }

    public void remove(final V fromVertex, final V intoVertex) {
        edges.getOrDefault(fromVertex, Collections.emptyMap()).remove(intoVertex);
    }

    public Set<V> get(final V fromVertex) {
        return edges.getOrDefault(fromVertex, Collections.emptyMap()).keySet();
    }

    public Optional<E> get(final V fromVertex, final V intoVertex) {
        return Optional.ofNullable(edges.getOrDefault(fromVertex, Collections.emptyMap()).get(intoVertex));
    }
}
