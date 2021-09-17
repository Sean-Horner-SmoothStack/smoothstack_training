public class SingletonObject {

    private final int secretNumber;
    private final String name;


    // Singleton objects hide their constructor behind the private access modifier and
    // provide a single static instance of themselves.
    private SingletonObject() {
        secretNumber = 1234;
        name = "John";
    }

    // marking the single instance as volatile makes it thread safe by creating a lock
    volatile public static SingletonObject instance = null;

    public static SingletonObject getInstance() {
        // if the instance hasn't been instantiated yet (i.e. it is null), then
        // this if block creates it.
        if (instance == null)
            instance = new SingletonObject();

        // synchronizing the code block on instance makes doubly sure that the
        // object is locked for a single thread's use at a time
        synchronized (instance) {
            return instance;
        }
    }

    public String getInfo() {
        return String.format(
                "This singleton's name is: %s and its secret number is: %d",
                this.name,
                this.secretNumber);
    }
}
