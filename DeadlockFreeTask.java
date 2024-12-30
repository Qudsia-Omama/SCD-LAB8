public class DeadlockFreeTask {
    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();
    public static final Object Lock3 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        Thread thread3 = new Thread(new Task3());

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Task1 implements Runnable {
        @Override
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding Lock1...");
                try { Thread.sleep(10); } catch (InterruptedException e) {}

                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding Lock1 & Lock2...");
                    try { Thread.sleep(10); } catch (InterruptedException e) {}

                    synchronized (Lock3) {
                        System.out.println("Thread 1: Holding Lock1, Lock2 & Lock3...");
                    }
                }
            }
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 2: Holding Lock1...");
                try { Thread.sleep(10); } catch (InterruptedException e) {}

                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding Lock1 & Lock2...");
                    try { Thread.sleep(10); } catch (InterruptedException e) {}

                    synchronized (Lock3) {
                        System.out.println("Thread 2: Holding Lock1, Lock2 & Lock3...");
                    }
                }
            }
        }
    }

    static class Task3 implements Runnable {
        @Override
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 3: Holding Lock1...");
                try { Thread.sleep(10); } catch (InterruptedException e) {}

                synchronized (Lock2) {
                    System.out.println("Thread 3: Holding Lock1 & Lock2...");
                    try { Thread.sleep(10); } catch (InterruptedException e) {}

                    synchronized (Lock3) {
                        System.out.println("Thread 3: Holding Lock1, Lock2 & Lock3...");
                    }
                }
            }
        }
    }
}