public class Demo extends Thread {

    public void run() {
        System.out.println(
                "Thread " + Thread.currentThread().getPriority() + " is running");
        System.out.println(
                "Thread " + Thread.currentThread().getId() + " is running");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            Demo d = new Demo();
            Demo d2 = new Demo();
            d.start();
            try {
                d.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            d2.start();
        }
    }
}