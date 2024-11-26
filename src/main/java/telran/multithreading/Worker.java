package telran.multithreading;

public class Worker extends Thread {
    private static final Object mutex1 = new Object();
    private static final Object mutex2 = new Object();

    @Override
    public void run() {
        f1();
        f2();
    }

    public void f1() {
        synchronized (mutex1) {
            threadSleep();
            int sum = purposelessCalculation(10);
            synchronized (mutex2) {
                System.out.println("f1 executed: " + sum);
            }
        }
    }

    public void f2() {
        synchronized (mutex2) {
            threadSleep();
            int sum = purposelessCalculation(5);
            synchronized (mutex1) {
                System.out.println("f2 executed: " + sum);
            }
        }
    }

    private void threadSleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    private int purposelessCalculation(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }
}
