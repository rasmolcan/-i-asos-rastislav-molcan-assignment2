package sk.stuba.fei.uim.asos.assignment2.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerContractCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public AtomicIntegerContractCounter() {
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void incrementCount(AtomicInteger count) {
        this.getCount().incrementAndGet();
    }
}
