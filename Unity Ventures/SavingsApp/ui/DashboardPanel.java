package ui;

import model.SavingsManager;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private SavingsManager manager;
    private JLabel balanceVal;
    private JLabel expenseVal;
    private JLabel rewardVal;
    private JLabel unwantedVal;

    public DashboardPanel(SavingsManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BACKGROUND_BLACK);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.TEXT_WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        cardsPanel.setBackground(Theme.BACKGROUND_BLACK);

        balanceVal = new JLabel("$0.00", SwingConstants.CENTER);
        expenseVal = new JLabel("$0.00", SwingConstants.CENTER);
        rewardVal = new JLabel("$0.00", SwingConstants.CENTER);
        unwantedVal = new JLabel("$0.00", SwingConstants.CENTER);

        cardsPanel.add(createCard("Total Balance", balanceVal, Theme.ACCENT_GREEN));
        cardsPanel.add(createCard("Total Expenses", expenseVal, Theme.TEXT_WHITE));
        cardsPanel.add(createCard("Unwanted Expenses", unwantedVal, Theme.DANGER_RED));
        cardsPanel.add(createCard("Rewards & Coupons", rewardVal, Theme.ACCENT_GREEN));

        add(cardsPanel, BorderLayout.CENTER);
        refresh();
    }

    private JPanel createCard(String title, JLabel valueLabel, Color valueColor) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        Theme.stylePanel(card);
        card.setBorder(BorderFactory.createCompoundBorder(
            card.getBorder(), BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(Theme.FONT_SUBTITLE);
        titleLabel.setForeground(Theme.TEXT_GRAY);

        valueLabel.setFont(Theme.FONT_TITLE);
        valueLabel.setForeground(valueColor);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    public void refresh() {
        balanceVal.setText(String.format("$%.2f", manager.getTotalBalance()));
        expenseVal.setText(String.format("$%.2f", manager.getTotalExpenses()));
        rewardVal.setText(String.format("$%.2f", manager.getTotalRewards()));
        unwantedVal.setText(String.format("$%.2f", manager.getUnwantedExpenses()));
    }
}
