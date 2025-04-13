import java.util.*;
class Transaction {
    String type;
    double amount;
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}
class Account {
    private double balance;
    private ArrayList<Transaction> transactions;
    public Account() {
        balance = 0.0;
        transactions = new ArrayList<>();
    }
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("$" + amount + " deposited successfully.");
    }
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance -= amount;
        transactions.add(new Transaction("Withdraw", amount));
        System.out.println("$" + amount + " withdrawn successfully.");
    }
    public void transfer(double amount, Account recipient) {
        if (amount > balance) {
            System.out.println("Insufficient balance for transfer!");
            return;
        }
        balance -= amount;
        recipient.deposit(amount);
        transactions.add(new Transaction("Transfer", amount));
        System.out.println("$" + amount + " transferred successfully.");
    }
    public void printTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }
        System.out.println("Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
    public double getBalance() {
        return balance;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account userAccount = new Account();
        System.out.println("Welcome to the ATM Interface");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        if (!userId.equals("user123") || !pin.equals("1234")) {
            System.out.println("Invalid credentials!");
            return;
        }
        System.out.println("Login successful!");
        while (true) {
            System.out.println("\n1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    userAccount.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    userAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    Account recipientAccount = new Account();
                    userAccount.transfer(transferAmount, recipientAccount);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}