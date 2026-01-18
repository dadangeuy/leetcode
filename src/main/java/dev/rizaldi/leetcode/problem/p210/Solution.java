package dev.rizaldi.leetcode.problem.p210;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * <p>210. Course Schedule II</p>
 * <p>Difficulty: Medium</p>
 * <p>Verdict: Accepted</p>
 * <p>Link: <a href="https://leetcode.com/problems/course-schedule-ii/description/">LeetCode</a></p>
 */
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        final Graph<Integer, int[]> prevCourses = new Graph<>();
        final Graph<Integer, int[]> nextCourses = new Graph<>();

        for (final int[] prerequisite : prerequisites) {
            final int course = prerequisite[0];
            final int prevCourse = prerequisite[1];

            prevCourses.add(course, prevCourse, prerequisite);
            nextCourses.add(prevCourse, course, prerequisite);
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
        final int[] courses = isFinishable ? finishedCourses.stream().mapToInt(i -> i).toArray() : new int[0];
        return courses;
    }
}

class Graph<V, E> {
    private final Map<V, Map<V, E>> edges = new HashMap<>();

    public void add(final V fromVertex, final V intoVertex, final E edge) {
        edges
            .computeIfAbsent(fromVertex, k -> new HashMap<>())
            .put(intoVertex, edge);
    }

    public void remove(final V fromVertex, final V intoVertex) {
        edges
            .getOrDefault(fromVertex, Collections.emptyMap())
            .remove(intoVertex);
    }

    public Set<V> get(final V fromVertex) {
        return edges.getOrDefault(fromVertex, Collections.emptyMap()).keySet();
    }

    public Optional<E> get(final V fromVertex, final V intoVertex) {
        return Optional.ofNullable(edges.getOrDefault(fromVertex, Collections.emptyMap()).get(intoVertex));
    }
}
