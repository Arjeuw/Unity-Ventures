import ui.MainFrame;
import ui.Theme;
import model.SavingsManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set cross-platform dark look and feel if possible
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Customizing Nimbus UI globally for scrollbars and pane colors (optional)
            UIManager.put("nimbusBase", Theme.BACKGROUND_BLACK);
            UIManager.put("nimbusBlueGrey", Theme.PANEL_BLACK);
            UIManager.put("control", Theme.PANEL_BLACK);

        } catch (Exception e) {
            // Default look and feel
        }

        SwingUtilities.invokeLater(() -> {
            SavingsManager manager = new SavingsManager();
            MainFrame frame = new MainFrame(manager);
            frame.setVisible(true);
        });
    }
}
