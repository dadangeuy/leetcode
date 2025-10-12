package dev.rizaldi.leetcode.contest.bc167.q3;

import java.util.TreeMap;

public class ExamTracker {
    private final TreeMap<Long, Long> scorePerTime = new TreeMap<>();
    private long currentTotal = 0;

    public ExamTracker() {
    }

    public void record(int time, int score) {
        currentTotal += score;
        scorePerTime.computeIfPresent((long) time, (k, v) -> v + currentTotal);
        scorePerTime.putIfAbsent((long) time, currentTotal);
    }

    public long totalScore(int startTime, int endTime) {
        Long t1 = scorePerTime.lowerKey((long) startTime);
        Long t2 = scorePerTime.floorKey((long) endTime);

        t1 = t1 == null? 0 : t1;
        t2 = t2 == null? 0 : t2;

        final long total = scorePerTime.getOrDefault(t2, 0L) - scorePerTime.getOrDefault(t1, 0L);

        return total;
    }
}
