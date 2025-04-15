import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Dashboard {

    public static void main(String[] args) {

        JFrame dash = new JFrame("Currency Exchange");
        dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dash.setFont(new Font("Arial", Font.BOLD, 30));
        dash.setSize(500, 700);
        dash.setResizable(false);
        dash.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 700);
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Currency Converter");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(150, 20, 220, 30);

        String[] currencies = { "PHP", "USD", "EUR", "IDR", "IRR", "KPW", "KRW", "BYN", "CHF", "CAD" };

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(20, 80, 50, 30);
        JComboBox<String> currencys = new JComboBox<>(currencies);
        currencys.setBounds(70, 80, 120, 30);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(210, 80, 30, 30);
        JComboBox<String> currency = new JComboBox<>(currencies);
        currency.setBounds(240, 80, 120, 30);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 130, 80, 30);
        JTextField amount = new JTextField();
        amount.setBounds(80, 130, 280, 30);

        JLabel resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setBounds(20, 170, 350, 30);

        JButton convert = new JButton("Convert");
        convert.setBounds(80, 210, 100, 40);
        JButton clear = new JButton("Clear");
        clear.setBounds(220, 210, 100, 40);

        JLabel addCurrencyLabel = new JLabel("Add Currency:");
        addCurrencyLabel.setBounds(20, 270, 100, 30);
        JTextField newCurrencyField = new JTextField();
        newCurrencyField.setBounds(120, 270, 60, 30);

        JLabel rateLabel = new JLabel("Rate:");
        rateLabel.setBounds(200, 270, 40, 30);
        JTextField newRateField = new JTextField();
        newRateField.setBounds(240, 270, 100, 30);

        JButton addCurrencyButton = new JButton("Add/Update");
        addCurrencyButton.setBounds(350, 270, 120, 30);

        JLabel deleteCurrencyLabel = new JLabel("Delete Currency:");
        deleteCurrencyLabel.setBounds(20, 310, 120, 30);
        JComboBox<String> deleteCurrencyBox = new JComboBox<>(currencies);
        deleteCurrencyBox.setBounds(140, 310, 100, 30);
        JButton deleteCurrencyButton = new JButton("Delete");
        deleteCurrencyButton.setBounds(260, 310, 100, 30);

        JLabel historyLabel = new JLabel("Conversion History:");
        historyLabel.setBounds(20, 360, 200, 20);

        DefaultListModel<String> historyModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(historyModel);
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setBounds(20, 390, 450, 180);

        JButton clearHistory = new JButton("Clear History");
        clearHistory.setBounds(180, 580, 140, 30);

        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("PHP", 50.0);
        rates.put("EUR", 0.85);
        rates.put("IDR", 14000.0);
        rates.put("IRR", 42000.0);
        rates.put("KPW", 900.0);
        rates.put("KRW", 1200.0);
        rates.put("BYN", 2.5);
        rates.put("CHF", 0.92);
        rates.put("CAD", 1.35);

        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String from = (String) currencys.getSelectedItem();
                    String to = (String) currency.getSelectedItem();
                    double inputAmount = Double.parseDouble(amount.getText());

                    if (from.equals(to)) {
                        JOptionPane.showMessageDialog(dash, "Please select different currencies.");
                        return;
                    }

                    double fromRate = rates.get(from);
                    double toRate = rates.get(to);
                    double converted = inputAmount * (toRate / fromRate);

                    String result = String.format("%.2f %s = %.2f %s", inputAmount, from, converted, to);
                    resultLabel.setText("Converted Amount: " + result);
                    historyModel.addElement(result);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dash, "Please enter a valid numeric amount.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dash, "Conversion error: " + ex.getMessage());
                }
            }
        });

        clear.addActionListener(e -> {
            amount.setText("");
            resultLabel.setText("Converted Amount: ");
            currencys.setSelectedIndex(0);
            currency.setSelectedIndex(0);
        });

        addCurrencyButton.addActionListener(e -> {
            String newCurrency = newCurrencyField.getText().toUpperCase();
            String rateText = newRateField.getText();
            try {
                double rate = Double.parseDouble(rateText);
                if (!rates.containsKey(newCurrency)) {
                    currencys.addItem(newCurrency);
                    currency.addItem(newCurrency);
                    deleteCurrencyBox.addItem(newCurrency);
                }
                rates.put(newCurrency, rate);
                JOptionPane.showMessageDialog(dash, newCurrency + " added/updated with rate " + rate);
                newCurrencyField.setText("");
                newRateField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dash, "Please enter a valid rate.");
            }
        });

        deleteCurrencyButton.addActionListener(e -> {
            String toDelete = (String) deleteCurrencyBox.getSelectedItem();
            if (toDelete.equals("USD")) {
                JOptionPane.showMessageDialog(dash, "You cannot delete the base currency USD.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(dash, "Are you sure you want to delete " + toDelete + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                rates.remove(toDelete);
                currencys.removeItem(toDelete);
                currency.removeItem(toDelete);
                deleteCurrencyBox.removeItem(toDelete);
                JOptionPane.showMessageDialog(dash, toDelete + " has been deleted.");
            }
        });

        clearHistory.addActionListener(e -> historyModel.clear());

        // Add components
        panel.add(title);
        panel.add(fromLabel);
        panel.add(currencys);
        panel.add(toLabel);
        panel.add(currency);
        panel.add(amountLabel);
        panel.add(amount);
        panel.add(resultLabel);
        panel.add(convert);
        panel.add(clear);
        panel.add(addCurrencyLabel);
        panel.add(newCurrencyField);
        panel.add(rateLabel);
        panel.add(newRateField);
        panel.add(addCurrencyButton);
        panel.add(deleteCurrencyLabel);
        panel.add(deleteCurrencyBox);
        panel.add(deleteCurrencyButton);
        panel.add(historyLabel);
        panel.add(scrollPane);
        panel.add(clearHistory);

        dash.add(panel);
        dash.setVisible(true);
    }
}
