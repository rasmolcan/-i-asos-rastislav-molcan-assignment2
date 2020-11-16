package sk.stuba.fei.uim.asos.assignment2.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerUserCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public AtomicIntegerUserCounter() {
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void incrementCount(AtomicInteger count) {
        this.getCount().incrementAndGet();
    }
}
