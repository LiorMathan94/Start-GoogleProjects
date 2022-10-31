package Exercise1;

import java.util.concurrent.atomic.AtomicInteger;

public class Guest {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public Guest() {
        this.id = count.incrementAndGet();
    }
}
