package intSeries_Common;

public class Int03_ThreadLocal {

    // ThreadLocal is mainly used when you are running something in parallel
    // and each thread needs its own separate data/object.

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 1. set(T value) - Sets a value for the current thread.
     */
    public void testSet() {
        threadLocal.set("Hello");
        System.out.println("Set value: " + threadLocal.get());

        threadLocal.set("Raj");
        String value = threadLocal.get();
        System.out.println("Updated value: " + value);
    }

    /**
     * 2. get() - Returns the value stored for the current thread.
     */
    public void testGet() {
        threadLocal.set("Raj");
        String value = threadLocal.get();
        System.out.println("Get value: " + value);
    }

    /**
     * 3. remove() - Removes the value associated with the current thread.
     */
    public void testRemove() {
        threadLocal.set("Raj");
        System.out.println("Before remove: " + threadLocal.get());

        threadLocal.remove();
        System.out.println("After remove: " + threadLocal.get());
    }

    /**
     * 4. initialValue() - Override to define initial value (legacy approach).
     */
    public void testInitialValue() {
        ThreadLocal<String> threadLocalWithDefault = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "Default Value";
            }
        };

        System.out.println("Initial value: " + threadLocalWithDefault.get());
    }

    /**
     * 5. withInitial(Supplier) - Modern alternative to overriding initialValue().
     */
    public void testWithInitial() {
        ThreadLocal<String> threadLocalWithSupplier = 
            ThreadLocal.withInitial(() -> "Default Value");

        System.out.println("Value with withInitial: " + threadLocalWithSupplier.get());
    }

    /**
     * 6. toString() - Returns a string representation of the object.
     */
    public void testToString() {
        System.out.println("ThreadLocal toString: " + threadLocal.toString());
    }

    /**
     * 7. equals(Object obj) - Checks whether two objects are equal.
     */
    public void testEquals() {
        ThreadLocal<String> t1 = new ThreadLocal<>();
        ThreadLocal<String> t2 = new ThreadLocal<>();

        System.out.println("t1.equals(t2): " + t1.equals(t2));
    }

    /**
     * 8. hashCode() - Returns the hash code of the ThreadLocal object.
     */
    public void testHashCode() {
        int hash = threadLocal.hashCode();
        System.out.println("Hash code: " + hash);
    }

    /**
     * 9. getClass() - Returns the runtime class.
     */
    public void testGetClass() {
        System.out.println("Class: " + threadLocal.getClass());
    }

    public static void main(String[] args) {
        Int03_ThreadLocal demo = new Int03_ThreadLocal();

        demo.testSet();
        demo.testGet();
        demo.testRemove();
        demo.testInitialValue();
        demo.testWithInitial();
        demo.testToString();
        demo.testEquals();
        demo.testHashCode();
        demo.testGetClass();
    }
}
