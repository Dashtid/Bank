import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    Bank BankSystem = new Bank(); // Renamed from Nordea
    private Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        BankApp bankApp = new BankApp();
        bankApp.runApplication();
    }

    public void runApplication() {
        menuSelection();
    }

    public void menu() {
        System.out.println("Welcome to the bank!");
        System.out.println("1. Find accounts by holder");
        System.out.println("2. Search account holders by (part of) name");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Create account");
        System.out.println("7. Remove account");
        System.out.println("8. Print all accounts");
        System.out.println("9. Exit");
        System.out.println("Enter a number to make a selection");
    }

    private void menuSelection() {
        int choice;
        do {
            menu();
            choice = getValidInt("Enter your choice:");
            switch (choice) {
                case 1 -> handleFindAccountsForHolder();
                case 2 -> handleFindByPartOfName();
                case 3 -> handleDeposit();
                case 4 -> handleWithdraw();
                case 5 -> handleTransfer();
                case 6 -> handleCreateAccount();
                case 7 -> handleRemoveAccount();
                case 8 -> handlePrintAllAccounts();
                case 9 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 9);
    }

    private void handleFindAccountsForHolder() {
        System.out.println("Enter ID number");
        long id = scan.nextLong();
        scan.nextLine(); // Consume the newline character
        ArrayList<BankAccount> accounts = BankSystem.findAccountsForHolder(id);
        if (accounts.isEmpty()) {
            System.out.println("No accounts found for the given ID.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
        }
    }

    private void handleFindByPartOfName() {
        System.out.println("Enter part of the name");
        String namePart = scan.next();
        namePart = namePart.trim();
        ArrayList<Customer> customers = BankSystem.findByPartofName(namePart);
        if (customers.isEmpty()) {
            System.out.println("No customers found with the given name part.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void handleWithdraw() {
        System.out.println("Enter your account number");
        int accountNumber = scan.nextInt();
        BankAccount account = BankSystem.findByAccountNumber(accountNumber); // Renamed
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("Enter the amount you want to withdraw");
        int amount = scan.nextInt();
        try {
            account.withdraw(amount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleTransfer() {
        System.out.println("Enter your account number");
        int fromAccountNumber = scan.nextInt();
        BankAccount fromAccount = BankSystem.findByAccountNumber(fromAccountNumber); // Renamed
        if (fromAccount == null) {
            System.out.println("Source account not found.");
            return;
        }
        System.out.println("Enter the account number you want to transfer to");
        int toAccountNumber = scan.nextInt();
        BankAccount toAccount = BankSystem.findByAccountNumber(toAccountNumber); // Renamed
        if (toAccount == null) {
            System.out.println("Destination account not found.");
            return;
        }
        System.out.println("Enter the amount you want to transfer");
        int amount = scan.nextInt();
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleCreateAccount() {
        System.out.println("Enter your personal ID number");
        long userId = scan.nextLong();
        scan.nextLine(); // Consume the newline character
        System.out.println("Enter your full name");
        String name = scan.nextLine();
        BankSystem.addAccount(name, userId); // Renamed
    }

    private void handleRemoveAccount() {
        System.out.println("Enter your account number");
        int accountNumber = scan.nextInt();
        if (BankSystem.removeAccount(accountNumber)) { // Renamed
            System.out.println("Account removed");
        } else {
            System.out.println("Account does not exist");
        }
    }

    private void handlePrintAllAccounts() {
        ArrayList<BankAccount> accounts = BankSystem.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
        }
    }

    private void handleDeposit() {
        try {
            System.out.println("Enter account number:");
            int accountNumber = scan.nextInt();
            double amount = getPositiveAmount("Enter amount to deposit:");

            BankAccount account = BankSystem.findByAccountNumber(accountNumber); // Renamed
            if (account != null) {
                account.deposit(amount);
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            scan.nextLine(); // Clear invalid input
        }
    }

    private double getPositiveAmount(String prompt) {
        double amount;
        do {
            System.out.println(prompt);
            amount = scan.nextDouble();
            if (amount <= 0) {
                System.out.println("Amount must be positive. Try again.");
            }
        } while (amount <= 0);
        return amount;
    }

    private int getValidInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine(); // Clear invalid input
            }
        }
    }
}

