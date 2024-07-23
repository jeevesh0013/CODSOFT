import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method - current balance
    public double getBalance() {
        return balance;
    }

    // Method - 1 deposit an amount into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $ " + amount);
        } else {
            System.out.println("Please enter a valid amount.");
        }
    }

    // Method to withdraw an amount from the account
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $ " + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount bankAccount;

    // Constructor to initialize the ATM with a bank account
    public ATM(BankAccount account) {
        this.bankAccount = account;
    }

    // Method to check the account balance
    public void checkBalance() {
        System.out.println("Current balance: $ " + bankAccount.getBalance());
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }

    // Method to display the ATM menu and handle user input
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    withdraw(withdrawalAmount);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}

// Main class to run the ATM machine simulation
public class ATMMachine {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000.00
        BankAccount userAccount = new BankAccount(1000.00);

        // Create an ATM instance with the user's bank account
        ATM atm = new ATM(userAccount);

        // Show the ATM menu
        atm.showMenu();
    }
}
