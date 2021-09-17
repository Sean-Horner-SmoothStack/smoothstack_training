public class SingletonObject {

    // Singleton objects hide their constructor behind the private access modifier and
    // provide a single static instance of themselves.
    private SingletonObject() {
        // constructor logic
    }

    // marking the single instance as volatile makes it thread safe by creating a lock
    volatile public static SingletonObject instance = null;

    public SingletonObject getInstance() {
        // synchronizing the code block on instance makes doubly sure that the
        // object is locked for a single thread's use at a time
        synchronized (instance) {
            // if the instance hasn't been instantiated yet (i.e. it is null), then
            // this if block creates it.
            if (instance == null)
                instance = new SingletonObject();
            return instance;
        }
    }
}
