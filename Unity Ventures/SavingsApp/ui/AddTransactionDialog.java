package ui;

import model.SavingsManager;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddTransactionDialog extends JDialog {
    private SavingsManager manager;
    private DashboardPanel dashboardPanel;
    private TransactionsPanel transactionsPanel;

    private JComboBox<Transaction.Type> typeCombo;
    private JTextField amountField;
    private JTextField categoryField;
    private JTextField descField;
    private JCheckBox unwantedCheck;

    public AddTransactionDialog(JFrame parent, SavingsManager manager, DashboardPanel dp, TransactionsPanel tp) {
        super(parent, "Add New Record", true);
        this.manager = manager;
        this.dashboardPanel = dp;
        this.transactionsPanel = tp;

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBackground(Theme.PANEL_BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel typeLabel = createLabel("Type:");
        typeCombo = new JComboBox<>(Transaction.Type.values());
        
        JLabel amountLabel = createLabel("Amount ($):");
        amountField = new JTextField();
        
        JLabel categoryLabel = createLabel("Category:");
        categoryField = new JTextField();
        
        JLabel descLabel = createLabel("Description:");
        descField = new JTextField();
        
        JLabel unwantedLabel = createLabel("Unwanted Expense?");
        unwantedCheck = new JCheckBox();
        unwantedCheck.setBackground(Theme.PANEL_BLACK);
        unwantedCheck.setForeground(Theme.TEXT_WHITE);

        panel.add(typeLabel); panel.add(typeCombo);
        panel.add(amountLabel); panel.add(amountField);
        panel.add(categoryLabel); panel.add(categoryField);
        panel.add(descLabel); panel.add(descField);
        panel.add(unwantedLabel); panel.add(unwantedCheck);

        JButton saveBtn = new JButton("Save Record");
        Theme.styleButton(saveBtn);
        saveBtn.addActionListener(e -> saveTransaction());
        
        JButton cancelBtn = new JButton("Cancel");
        Theme.styleDangerButton(cancelBtn);
        cancelBtn.addActionListener(e -> dispose());

        panel.add(saveBtn);
        panel.add(cancelBtn);

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        getContentPane().setBackground(Theme.BACKGROUND_BLACK);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Theme.TEXT_WHITE);
        label.setFont(Theme.FONT_REGULAR);
        return label;
    }

    private void saveTransaction() {
        try {
            Transaction.Type type = (Transaction.Type) typeCombo.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            String desc = descField.getText();
            boolean isUnwanted = unwantedCheck.isSelected() && type == Transaction.Type.EXPENSE;
            
            if (category.isEmpty()) category = "General";

            manager.addTransaction(LocalDate.now(), amount, category, desc, type, isUnwanted);
            
            // Refresh UI
            dashboardPanel.refresh();
            transactionsPanel.refresh();
            
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
