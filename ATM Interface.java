import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                System.out.println("Current Balance: " + bankAccount.getBalance());
                break;
            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = getValidAmount();
                bankAccount.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = getValidAmount();
                bankAccount.withdraw(withdrawalAmount);
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    private double getValidAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount = 0;

        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid positive amount: ");
            }
        }

        return amount;
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(10000.0); // Initial balance
        ATM atm = new ATM(userAccount);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice (1-4): ");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                atm.processOption(option);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}