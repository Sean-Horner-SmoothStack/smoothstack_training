public class PurposefulDeadlock {

    // create two threads accessing two objects (primitive) that each other wants

    public static void main(String[] args) {

        String str1 = "This string is cool.";
        String str2 = "So is this one!";

        Runnable thread_1 = new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (str1) {
                        Thread.sleep(1000);
                        synchronized (str2) {
                            System.out.println(str1 + "\n" + str2);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable thread_2 = new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (str2) {
                        Thread.sleep(1000);
                        synchronized (str1) {
                            System.out.println(str1 + "\n" + str2);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        System.out.println("Starting the deadlock test, should print out two lines.");

        new Thread(thread_1).start();
        new Thread(thread_2).start();

        System.out.println("Did anything else print?");
    }
}
