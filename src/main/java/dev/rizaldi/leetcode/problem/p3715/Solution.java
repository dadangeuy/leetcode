package dev.rizaldi.leetcode.problem.p3715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>3715. Sum of Perfect Square Ancestors</p>
 * <p>Difficulty: Hard</p>
 * <p>Verdict: Accepted</p>
 * <p>Link: <a href="https://leetcode.com/problems/sum-of-perfect-square-ancestors/">LeetCode</a></p>
 */
public class Solution {
    private static final Map<Integer, List<Integer>> CACHE_PRIME_FACTORS = new HashMap<>();

    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        final int max = Arrays.stream(nums).max().orElse(0);
        final Set<Integer> primes = getPrimes(max);
        final List<Integer> oddFactors = getOddFactors(primes, nums);

        final List<Vertex> vertexList = new ArrayList<>(n);
        for (int node = 0; node < n; node++) {
            final Vertex vertex = new Vertex(node, nums[node], oddFactors.get(node));
            vertexList.add(vertex);
        }

        final List<Edge<Vertex>> edgeList = new ArrayList<>(edges.length);
        for (int[] fromAndInto : edges) {
            final Vertex from = vertexList.get(fromAndInto[0]);
            final Vertex into = vertexList.get(fromAndInto[1]);
            final Edge<Vertex> edge = new Edge<>(from, into);
            edgeList.add(edge);
        }

        final Graph<Vertex, Edge<Vertex>> graph = new Graph<>();
        for (final Edge<Vertex> edge : edgeList) {
            graph.add(edge.from, edge.into, edge);
            graph.add(edge.into, edge.from, edge.reverse());
        }

        return depthFirstSearch(
                graph,
                vertexList.getFirst(),
                new HashSet<>(),
                new CountMap<>()
        );
    }

    private static LinkedHashSet<Integer> getPrimes(final int max) {
        final LinkedHashSet<Integer> primes = new LinkedHashSet<>(10_000);

        final boolean[] isPrimes = new boolean[max + 1];
        Arrays.fill(isPrimes, true);

        isPrimes[0] = false;
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                primes.add(i);
                for (int j = i + i; j < isPrimes.length; j += i) {
                    isPrimes[j] = false;
                }
            }
        }
        return primes;
    }

    private List<Integer> getOddFactors(final Set<Integer> primes, final int[] nums) {
        final List<Integer> oddFactors = new LinkedList<>();
        for (final int num : nums) {
            final List<Integer> factors = getPrimeFactors(primes, num);
            int oddFactor = 1;
            for (int left = 0, right = 0; left < factors.size(); left = right) {
                for (; right < factors.size() && factors.get(left).equals(factors.get(right)); right++) ;
                final int count = right - left;
                final boolean isOdd = count % 2 != 0;
                if (isOdd) oddFactor *= factors.get(left);
            }
            oddFactors.add(oddFactor);
        }
        return new ArrayList<>(oddFactors);
    }

    private List<Integer> getPrimeFactors(final Set<Integer> primes, final int number) {
        return CACHE_PRIME_FACTORS.computeIfAbsent(number, n -> doGetPrimeFactors(primes, n));
    }

    private List<Integer> doGetPrimeFactors(final Set<Integer> primes, final int number) {
        if (primes.contains(number)) return Collections.singletonList(number);

        final List<Integer> factors = new LinkedList<>();
        int remaining = number;
        for (final int prime : primes) {
            if (prime == 1) continue;
            if (remaining <= 1) break;
            while (remaining > 1L && remaining % prime == 0) {
                remaining /= prime;
                factors.add(prime);
            }
        }

        return new ArrayList<>(factors);
    }

    private long depthFirstSearch(
            final Graph<Vertex, Edge<Vertex>> graph,
            final Vertex vertex,
            final Set<Vertex> visited,
            final CountMap<Integer> oddFactorCounts
    ) {
        long count = 0;
        count += oddFactorCounts.get(vertex.oddFactor);

        visited.add(vertex);
        oddFactorCounts.increment(vertex.oddFactor, 1);

        for (final Edge<Vertex> edge : graph.get(vertex)) {
            if (!visited.contains(edge.into)) {
                count += depthFirstSearch(graph, edge.into, visited, oddFactorCounts);
            }
        }

        oddFactorCounts.increment(vertex.oddFactor, -1);
        return count;
    }
}

class Vertex {
    public final int node;
    public final int num;
    public final int oddFactor;

    public Vertex(final int node, final int num, final int oddFactor) {
        this.node = node;
        this.num = num;
        this.oddFactor = oddFactor;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof final Vertex vertex) {
            return vertex.node == node;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return node;
    }
}

class Edge<V> {
    public final V from;
    public final V into;

    public Edge(final V from, final V into) {
        this.from = from;
        this.into = into;
    }

    public Edge<V> reverse() {
        return new Edge<>(into, from);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof final Edge<?> edge) {
            return from.equals(edge.from) && into.equals(edge.into);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, into);
    }
}

class Graph<V, E> {
    private final Map<V, Map<V, E>> edges = new HashMap<>();

    public void add(final V fromVertex, final V intoVertex, final E edge) {
        edges
                .computeIfAbsent(fromVertex, k -> new HashMap<>())
                .put(intoVertex, edge);
    }

    public Collection<E> get(final V fromVertex) {
        return edges
                .getOrDefault(fromVertex, Collections.emptyMap())
                .values();
    }
}

class CountMap<I> {
    public final Map<I, Long> counts = new HashMap<>();

    public void increment(final I item, final long amount) {
        counts.put(item, amount + counts.getOrDefault(item, 0L));
        if (counts.getOrDefault(item, 0L) <= 0L) counts.remove(item);
    }

    public long get(final I item) {
        return counts.getOrDefault(item, 0L);
    }
}
