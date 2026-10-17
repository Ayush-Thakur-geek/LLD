package multithreading;

public class RunnableExample {
    public static void main(String[] args) {
        Ex ex = new Ex();
        Thread exThread = new Thread(ex);
        exThread.start();
    }
}

class Ex implements Runnable {
    @Override
    public void run() {
        System.out.println("RunnableExample run");
    }
}
