import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// Main class for the bancjing application GUI
public class BankingApplicationGUI{
    public static void main(String[] args){
        // Create a BankAccountGUI object and show the menu
        BankAccount bank1 = new BankAccount("Cris", "0001");
        bank1.showMenu();
    }
}

// GUI representing a bank account with GUI components
class BankAccount{
    // Variables
    int balance;
    int previousTransaction;
    String customerName;
    String customerId;

    // GUI components
    JFrame frame; // Maine frame of the application
    JLabel balanceLabel; // Label to display the balance
    JTextField amountField; // Text field to input the amount
    JTextArea outputArea; // Text area to display messages

    // Set customer name and ID
    BankAccount(String cname, String cid){
        customerName = cname; 
        customerId = cid;
    }

    // Method to deposit the amount into the account
    void deposit(int amount){
        if (amount !=0){ // check if the amount is not zero
            balance = balance + amount; // add the amount to the balance
            previousTransaction = amount; 
            updateBalance(); 
        }
    }
    
    // Method to withdraw an amount from the account
    void withdraw(int amount){
        if (amount != 0){ // check if the amount is not zero
            balance = balance - amount; // subtract the amount from the balance
            previousTransaction = -amount;
            updateBalance();
        }
    }

    // Method to display the previous transaction
    void getPreviousTransaction(){
        if (previousTransaction > 0){ // check if previous trans. was a deposit
            System.out.println("Deposited: "+ previousTransaction);
        } else if(previousTransaction < 0) {   //  check if privious trans. was a withdraw
            System.out.println("Withdrawn: "+ Math.abs(previousTransaction));    
        } else { // the previous trans. was 0$ or this is a new account
            System.out.println("No transaction is occured!");
        }
    }

    // method to update the balance label
    void updateBalance(){
        balanceLabel.setText("Balance: $" + balance);
    }

    // Method to create and display the GUI
    void showMenu(){
        frame = new JFrame("Banking Application"); // create the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation
        frame.setSize(400, 400); // set the size of the frame

        Container container = frame.getContentPane(); // get the content pane of the frame
        container.setLayout(new BorderLayout()); // set the layout to BorderLayout

        // Top panel with customer information and balance
        JPanel topPanel = new JPanel(); // panel for top section
        topPanel.setLayout(new FlowLayout()); // set layout to FlowLayout
        JLabel welcomLabel = new JLabel("Welcome" + customerName); // Create a label with the customer's name
        JLabel idLabel = new JLabel("Your ID: " + customerId);  // ...
        balanceLabel = new JLabel();  // to display ...
        
        // ... add
        topPanel.add(welcomLabel);
        topPanel.add(idLabel);
        topPanel.add(balanceLabel);
        container.add(topPanel, BorderLayout.NORTH);

        // Center panel with action buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 1));

        // Create acyion buttons
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton previousTransactionButton = new JButton("Previouse Transaction");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the center panel
        centerPanel.add(checkBalanceButton);
        centerPanel.add(depositButton);
        centerPanel.add(withdrawButton);
        centerPanel.add(previousTransactionButton);
        centerPanel.add(exitButton);

        //... add the center panel to the center section of the container
        container.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with amount input and output area
        JPanel bottomPanel = new JPanel(); // create a panel to the bottom section
        bottomPanel.setLayout(new FlowLayout()); // set the layout to FlowLayout
        JLabel amountLabel = new JLabel("Enter the amount: ");
        amountField = new JTextField(10); // text field for entering of amount
        outputArea = new JTextArea(5, 30);  // text area to display message
        outputArea.setEditable(false); // make text area - non-editable

        // ... set label, field at/and bottom panel
        bottomPanel.add(amountLabel);
        bottomPanel.add(amountField);
        bottomPanel.add(new JScrollPane(outputArea));
        container.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons

        // Action listener for the check balance button
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateBalance();
            }
        });

        // Action listener for the deposit button
        depositButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int amount = Integer.parseInt(amountField.getText()); // get the amount from the text field
                deposit(amount); // call the deposit method
                outputArea.setText("Deposited: $" + amount); // display the deposited amount
            }
        });

        // Action listener for the previous withdraw button
        withdrawButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int amount = Integer.parseInt(amountField.getText()); // get the amount from the text field
                withdraw(amount); // call the withdraw method
                outputArea.setText("Withdrawn: $" + amount); // display the withdrawn amount
            }
        });
        
        // Action listener for the previous transaction button
        previousTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getPreviousTransaction();
            }
        });

        // Action listener for exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}