package ezw.swing;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public abstract class ManualLayout {

    private ManualLayout() {}

    public static void registerLayout(Window parent, Consumer<ComponentEvent> layout) {
        parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                layout.accept(e);
            }
        });
        parent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                layout.accept(e);
            }
        });
    }

    public static void putInTopLeftCorner(Component component, int margin) {
        component.setLocation(margin, margin);
    }

    public static void putInTopRightCornerIn(Component parent, Component component, int margin) {
        component.setLocation(parent.getWidth() - component.getWidth() - margin, margin);
    }

    public static void putInBottomLeftCornerIn(Component parent, Component component, int margin) {
        component.setLocation(margin, parent.getHeight() - component.getHeight() - margin);
    }

    public static void putInBottomRightCornerIn(Component parent, Component component, int margin) {
        component.setLocation(parent.getWidth() - component.getWidth() - margin, parent.getHeight() -
                component.getHeight() - margin);
    }

    public static void stretchRightIn(Component parent, Component component, int margin) {
        component.setSize(parent.getWidth() - component.getX() - margin, component.getHeight());
    }

    public static void stretchDownIn(Component parent, Component component, int margin) {
        component.setSize(component.getWidth(), parent.getHeight() - component.getY() - margin);
    }

    public static void putBelow(Component reference, Component component, int margin) {
        component.setLocation(reference.getX(), reference.getY() + reference.getHeight() + margin);
    }

    public static void putAbove(Component reference, Component component, int margin) {
        component.setLocation(reference.getX(), reference.getY() - component.getHeight() - margin);
    }

    public static void putToTheRightOf(Component reference, Component component, int margin) {
        component.setLocation(reference.getX() + reference.getWidth() + margin, reference.getY());
    }

    public static void putToTheLeftOf(Component reference, Component component, int margin) {
        component.setLocation(reference.getX() - component.getWidth() - margin, reference.getY());
    }

    public static void stretchDownTowards(Component reference, Component component, int margin) {
        component.setSize(component.getWidth(), reference.getY() - component.getY() - margin);
    }

    public static void stretchRightTowards(Component reference, Component component, int margin) {
        component.setSize(reference.getX() - component.getX() - margin, component.getY());
    }
}
