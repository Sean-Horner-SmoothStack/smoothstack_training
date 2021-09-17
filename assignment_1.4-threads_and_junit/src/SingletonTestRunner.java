public class SingletonTestRunner {

    public static void main(String[] args) {
//        // trying to call the SingletonObject from its constructor
//        try {
//            SingletonObject so = new SingletonObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // creating two objects from the
        SingletonObject so1 = SingletonObject.getInstance();
        SingletonObject so2 = SingletonObject.getInstance();

        System.out.println("Printing out both objects hash codes.\nIf they're the same, then they're the same object.");
        System.out.println("SingletonObject 1: " + so1.hashCode());
        System.out.println("SingletonObject 2: " + so2.hashCode());
    }

}
