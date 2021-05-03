package ezw.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingDemo {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JButton button1;
    private static JProgressBar progress;
    private static JButton button2;
    private static JButton button3;
    private static JButton button4;
    private static JScrollPane scroll;

    public static void main(String[] args) {
        Appearance.setNimbusBlack();

        frame = new JFrame("EZWork Swing Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 330);
        frame.setMinimumSize(new Dimension(400, 230));
        frame.setIconImage(getIcon());
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JTextArea text = new JTextArea(("Something to display. ".repeat(6).strip() + '\n').repeat(15).strip());
        text.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        scroll = new JScrollPane(text);
        scroll.setBorder(null);
        scroll.setBounds(panel.getBounds());
        panel.add(scroll);

        Dimension buttonSize = new Dimension(100, 25);

        label = new JLabel("test", JLabel.CENTER);
        label.setSize(buttonSize);
        panel.add(label);

        button1 = new JButton("Open");
        button1.setSize(buttonSize);
        panel.getRootPane().setDefaultButton(button1);
        button1.addActionListener(e -> new JFileChooser().showOpenDialog(frame));
        panel.add(button1);

        progress = new JProgressBar();
        progress.setSize(buttonSize);
        progress.setValue(20);
        panel.add(progress);

        button2 = new JButton("Cancel");
        button2.setSize(buttonSize);
        button2.setToolTipText("Cancel");
        Appearance.setButtonDarken(button2);
        panel.add(button2);

        button3 = new JButton("Disabled");
        button3.setSize(buttonSize);
        button3.setToolTipText("Disabled");
        button3.setEnabled(false);
        panel.add(button3);

        button4 = new JButton("OK");
        button4.setSize(buttonSize);
        button4.setToolTipText("OK");
        panel.add(button4);

        ManualLayout.registerLayout(frame, e -> layout());
        frame.setVisible(true);
    }

    private static void layout() {
        ManualLayout.putInTopLeftCorner(scroll, 2);
        ManualLayout.stretchRightIn(panel, scroll, 2);
        ManualLayout.putInBottomLeftCornerIn(panel, progress, 4);
        ManualLayout.putToTheRightOf(progress, button3, 10);
        ManualLayout.putAbove(progress, button1, 8);
        ManualLayout.putAbove(button3, button2, 8);
        ManualLayout.putInBottomRightCornerIn(panel, button4, 4);
        ManualLayout.putAbove(button1, label, 8);
        ManualLayout.stretchDownTowards(label, scroll, 8);
        scroll.revalidate();
        frame.repaint();
    }

    private static Image getIcon() {
        var icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB_PRE);
        var g2d = icon.createGraphics();
        Appearance.applyQualityRendering(g2d);
        g2d.setFont(new Font("Courier", Font.PLAIN, 12));
        g2d.drawString("Demo", 0, 20);
        return icon;
    }
}
