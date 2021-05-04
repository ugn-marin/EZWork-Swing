package ezw.swing;

import javax.swing.*;
import java.awt.*;

public abstract class Appearance {

    private Appearance() {}

    public static void setNimbus() {
        setNimbus(null);
    }

    public static void setNimbus(Color base) {
        setNimbus(base, null, null, null);
    }

    public static void setNimbusLight(Color base, Color focus) {
        setNimbus(base, focus, Color.darkGray, mix(Color.white, Color.lightGray, 0.1D));
    }

    public static void setNimbusDark(Color base, Color focus) {
        setNimbus(base, focus, Color.white, mix(Color.darkGray, Color.black, 0.1D));
    }

    public static void setNimbusWhite() {
        setNimbusWhite(null);
    }

    public static void setNimbusWhite(Color focus) {
        setNimbusLight(Color.lightGray, focus);
    }

    public static void setNimbusBlack() {
        setNimbusBlack(null);
    }

    public static void setNimbusBlack(Color focus) {
        setNimbusDark(Color.black, focus);
    }

    public static void setNimbusDarkBlueish() {
        var blueish = mix(Color.blue, Color.green, 0.2D);
        setNimbus(mix(mix(blueish, Color.darkGray, 0.7D).darker(), Color.black, 0.4D),
                mix(blueish, Color.gray, 0.4D).brighter(),
                mix(blueish, Color.lightGray, 0.9D).brighter(),
                mix(blueish.darker(), Color.darkGray, 0.9D));
    }

    public static void setNimbus(Color base, Color focus, Color foreground, Color background) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    if (base != null) {
                        UIManager.put("nimbusBase", base);
                        UIManager.put("nimbusBlueGrey", mix(base.brighter(), Color.lightGray, 0.45D));
                    }
                    if (focus != null) {
                        UIManager.put("nimbusFocus", focus);
                        UIManager.put("nimbusOrange", focus.brighter());
                        UIManager.put("nimbusSelectionBackground", mix(focus.darker(), Color.darkGray, 0.7D));
                        UIManager.put("nimbusSelectedText", Color.white);
                    }
                    if (foreground != null) {
                        UIManager.put("text", foreground);
                        UIManager.put("nimbusDisabledText", mix(foreground, Color.gray, 0.3D));
                    }
                    if (background != null) {
                        UIManager.put("control", background);
                        UIManager.put("info", mix(background, Color.gray, 0.2D));
                        UIManager.put("nimbusLightBackground", background.brighter());
                    }
                    break;
                }
            }
        } catch (Exception ignore) {}
    }

    public static void setButtonBaseColor(JButton button) {
        try {
            button.setBackground((Color) UIManager.get("nimbusBase"));
        } catch (Exception ignore) {}
    }

    public static Color mix(Color... colors) {
        if (colors.length == 0)
            return Color.gray;
        var color = colors[0];
        for (int i = 1; i < colors.length; i++) {
            color = mix(color, colors[i], 1D / (i + 1));
        }
        return color;
    }

    public static Color mix(Color base, Color add, double amount) {
        return new Color(mix(base.getRed(), add.getRed(), amount),
                mix(base.getGreen(), add.getGreen(), amount),
                mix(base.getBlue(), add.getBlue(), amount),
                mix(base.getAlpha(), add.getAlpha(), amount));
    }

    private static int mix(int base, int add, double amount) {
        return (int) (((double) base) * (1D - amount) + ((double) add) * amount);
    }

    public static void applyQualityRendering(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }
}
