package ezw.swing;

import ezw.concurrent.Concurrent;
import ezw.util.os.JVM;

import java.util.concurrent.ExecutionException;

public class SwingDemoJVM {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Concurrent.getAll(Concurrent.calculate(new JVM(SwingDemo.class)),
                Concurrent.calculate(new JVM(SwingDemo.class)));
    }
}
