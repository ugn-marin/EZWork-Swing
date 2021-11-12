package ezw.swing;

import ezw.concurrent.Concurrent;
import ezw.function.Reducer;
import ezw.runtime.JVM;

public class SwingDemoJVM {

    public static void main(String[] args) throws Exception {
        Concurrent.getAll(Reducer.last(), Concurrent.run(new JVM(SwingDemo.class)),
                Concurrent.run(new JVM(SwingDemo.class)));
        Concurrent.join();
    }
}
