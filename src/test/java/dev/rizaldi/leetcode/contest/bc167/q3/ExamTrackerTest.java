package dev.rizaldi.leetcode.contest.bc167.q3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExamTrackerTest {
    @Test
    public void example1() {
        final var examTracker = new ExamTracker();
        examTracker.record(1, 98);
        Assertions.assertEquals(98, examTracker.totalScore(1, 1));
        examTracker.record(5, 99);
        Assertions.assertEquals(98, examTracker.totalScore(1, 3));
        Assertions.assertEquals(197, examTracker.totalScore(1, 5));
        Assertions.assertEquals(0, examTracker.totalScore(3, 4));
        Assertions.assertEquals(99, examTracker.totalScore(2, 5));
    }

    @Test
    public void wa1() {
        final var examTracker = new ExamTracker();
        examTracker.record(999999996, 999999996);
        examTracker.record(999999997,999999997);
        examTracker.record(999999998,999999998);
        examTracker.record(999999999,999999999);
        examTracker.record(1000000000,1000000000);
        Assertions.assertEquals(4999999990L, examTracker.totalScore(1,1000000000));
    }
}
