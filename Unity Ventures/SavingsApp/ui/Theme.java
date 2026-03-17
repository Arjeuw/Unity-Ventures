package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Theme {
    public static final Color BACKGROUND_BLACK = new Color(18, 18, 18);
    public static final Color PANEL_BLACK = new Color(30, 30, 30);
    public static final Color ACCENT_GREEN = new Color(46, 204, 113);
    public static final Color ACCENT_GREEN_HOVER = new Color(39, 174, 96);
    public static final Color DANGER_RED = new Color(231, 76, 60);
    public static final Color TEXT_WHITE = new Color(240, 240, 240);
    public static final Color TEXT_GRAY = new Color(170, 170, 170);

    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 18);

    public static void styleButton(JButton button) {
        button.setBackground(ACCENT_GREEN);
        button.setForeground(BACKGROUND_BLACK);
        button.setFont(FONT_BOLD);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_GREEN_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_GREEN);
            }
        });
    }
    
    public static void styleDangerButton(JButton button) {
        button.setBackground(DANGER_RED);
        button.setForeground(TEXT_WHITE);
        button.setFont(FONT_BOLD);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    public static void stylePanel(JComponent panel) {
        panel.setBackground(PANEL_BLACK);
        panel.setBorder(new LineBorder(new Color(50, 50, 50), 1, true));
    }
}
