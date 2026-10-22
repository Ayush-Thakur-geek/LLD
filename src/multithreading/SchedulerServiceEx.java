package multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerServiceEx {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2  );
        scheduledExecutorService.schedule(() -> System.out.println("Run once"), 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("run infinitely"), 0, 2, TimeUnit.SECONDS);

    }
}
