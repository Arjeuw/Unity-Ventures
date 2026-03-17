package ui;

import model.SavingsManager;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionsPanel extends JPanel {
    private SavingsManager manager;
    private JTable table;
    private DefaultTableModel tableModel;

    public TransactionsPanel(SavingsManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout(10, 10));
        setBackground(Theme.BACKGROUND_BLACK);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Recent Transactions");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.TEXT_WHITE);
        add(titleLabel, BorderLayout.NORTH);

        String[] columns = {"Date", "Type", "Category", "Description", "Amount"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // read-only
            }
        };

        table = new JTable(tableModel);
        table.setBackground(Theme.PANEL_BLACK);
        table.setForeground(Theme.TEXT_WHITE);
        table.setFont(Theme.FONT_REGULAR);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(Theme.ACCENT_GREEN);
        table.getTableHeader().setForeground(Theme.BACKGROUND_BLACK);
        table.getTableHeader().setFont(Theme.FONT_BOLD);
        table.setGridColor(new Color(50, 50, 50));
        table.setFillsViewportHeight(true);

        // Custom renderer to highlight unwanted expenses
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Get the transaction from the manager based on row index
                List<Transaction> txs = manager.getTransactions();
                if (row < txs.size()) {
                    Transaction t = txs.get(txs.size() - 1 - row); // Display in reverse chronological if we insert at end
                    if (t.isUnwanted()) {
                        c.setForeground(Theme.DANGER_RED);
                    } else if (t.getType() == Transaction.Type.INCOME || t.getType() == Transaction.Type.REWARD) {
                        c.setForeground(Theme.ACCENT_GREEN);
                    } else {
                        c.setForeground(Theme.TEXT_WHITE);
                    }
                }
                
                if (isSelected) {
                    c.setBackground(new Color(60, 60, 60));
                } else {
                    c.setBackground(Theme.PANEL_BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Theme.BACKGROUND_BLACK);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        tableModel.setRowCount(0);
        List<Transaction> txs = manager.getTransactions();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        // Iterate backwards to show newest first
        for (int i = txs.size() - 1; i >= 0; i--) {
            Transaction t = txs.get(i);
            String sign = (t.getType() == Transaction.Type.EXPENSE) ? "-" : "+";
            tableModel.addRow(new Object[]{
                    t.getDate().format(formatter),
                    t.getType(),
                    t.getCategory(),
                    t.getDescription(),
                    sign + String.format("$%.2f", t.getAmount())
            });
        }
    }
}
