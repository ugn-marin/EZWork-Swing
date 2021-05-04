package ezw.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static ezw.swing.Appearance.*;
import static ezw.swing.ManualLayout.*;

public class SwingDemo extends JFrame {

    private final JPanel panel;
    private final JLabel label;
    private final JButton button1;
    private final JProgressBar progress;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JScrollPane scroll;

    public static void main(String[] args) {
        setNimbusBlack();
        new SwingDemo().setVisible(true);
    }

    SwingDemo() {
        super("EZWork Swing Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 330);
        setMinimumSize(new Dimension(400, 230));
        setIconImage(getIcon());
        setLocationRelativeTo(null);

        Dimension buttonSize = new Dimension(100, 25);

        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                var g2d = (Graphics2D) g;
                applyQualityRendering(g2d);
                int margin = 3;
                g2d.setColor(mix(Color.lightGray, Color.red));
                g2d.drawRoundRect(button2.getX() + margin, label.getY() + margin, buttonSize.width - margin * 2,
                        label.getHeight() - margin * 2, 6, 6);
            }
        };
        panel.setLayout(null);
        add(panel);

        JTextArea text = new JTextArea(("Something to display. ".repeat(6).strip() + '\n').repeat(15).strip());
        text.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        scroll = new JScrollPane(text);
        scroll.setBorder(null);
        scroll.setBounds(panel.getBounds());
        panel.add(scroll);

        label = new JLabel("test", JLabel.CENTER);
        label.setSize(buttonSize);
        panel.add(label);

        button1 = new JButton("Open");
        button1.setSize(buttonSize);
        panel.getRootPane().setDefaultButton(button1);
        button1.addActionListener(e -> new JFileChooser().showOpenDialog(this));
        panel.add(button1);

        progress = new JProgressBar();
        progress.setSize(buttonSize);
        progress.setValue(20);
        panel.add(progress);

        button2 = new JButton("Cancel");
        button2.setSize(buttonSize);
        button2.setToolTipText("Cancel");
        setButtonBaseColor(button2);
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

        registerLayout(this, e -> manualLayout());
    }

    private void manualLayout() {
        putInTopLeftCorner(scroll, 2);
        stretchRightIn(panel, scroll, 2);
        putInBottomLeftCornerIn(panel, progress, 4);
        putToTheRightOf(progress, button3, 10);
        putAbove(progress, button1, 8);
        putAbove(button3, button2, 8);
        putInBottomRightCornerIn(panel, button4, 4);
        putAbove(button1, label, 8);
        stretchDownTowards(label, scroll, 8);
        scroll.revalidate();
        repaint();
    }

    private Image getIcon() {
        var icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB_PRE);
        var g2d = icon.createGraphics();
        applyQualityRendering(g2d);
        g2d.setFont(new Font("Courier", Font.PLAIN, 12));
        g2d.drawString("Demo", 0, 20);
        return icon;
    }
}
