package multithreading;

import java.util.concurrent.Executor;

public class ExecutorEx {
    public static void main(String[] args) {
        Executor executor = command -> new Thread(command).start();
        executor.execute(() -> System.out.println("Hello World!"));
    }
}
