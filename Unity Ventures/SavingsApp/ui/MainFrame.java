package ui;

import model.SavingsManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SavingsManager manager;
    private DashboardPanel dashboardPanel;
    private TransactionsPanel transactionsPanel;

    public MainFrame(SavingsManager manager) {
        this.manager = manager;
        initUI();
    }

    private void initUI() {
        setTitle("Savings Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND_BLACK);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Theme.BACKGROUND_BLACK);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel appTitle = new JLabel("Personal Savings & Expense Tracker");
        appTitle.setFont(Theme.FONT_TITLE);
        appTitle.setForeground(Theme.ACCENT_GREEN);
        headerPanel.add(appTitle, BorderLayout.WEST);

        JButton addBtn = new JButton("+ Add Transaction");
        Theme.styleButton(addBtn);
        addBtn.addActionListener(e -> openAddDialog());
        headerPanel.add(addBtn, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Content (Split Pane for Dashboard and Transactions)
        dashboardPanel = new DashboardPanel(manager);
        transactionsPanel = new TransactionsPanel(manager);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dashboardPanel, transactionsPanel);
        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(5);
        splitPane.setBorder(null);
        splitPane.setBackground(Theme.BACKGROUND_BLACK);

        add(splitPane, BorderLayout.CENTER);
    }

    private void openAddDialog() {
        AddTransactionDialog dialog = new AddTransactionDialog(this, manager, dashboardPanel, transactionsPanel);
        dialog.setVisible(true);
    }
}
