import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    Bank Nordea = new Bank();
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
            choice = scan.nextInt();
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
        ArrayList<BankAccount> accounts = Nordea.findAccountsForHolder(id);
        System.out.println(accounts);
    }

    private void handleFindByPartOfName() {
        System.out.println("Enter part of the name");
        String namePart = scan.next();
        namePart = namePart.trim();
        ArrayList<Customer> customers = Nordea.findByPartofName(namePart);
        System.out.println(customers);
    }

    private void handleWithdraw() {
        System.out.println("Enter your account number");
        int accountNumber = scan.nextInt();
        System.out.println("Enter the amount you want to withdraw");
        int amount = scan.nextInt();
        Nordea.findByAccountNumber(accountNumber).withdraw(amount);
    }

    private void handleTransfer() {
        System.out.println("Enter your account number");
        int fromAccountNumber = scan.nextInt();
        System.out.println("Enter the account number you want to transfer to");
        int toAccountNumber = scan.nextInt();
        System.out.println("Enter the amount you want to transfer");
        int amount = scan.nextInt();
        Nordea.findByAccountNumber(fromAccountNumber).withdraw(amount);
        Nordea.findByAccountNumber(toAccountNumber).deposit(amount);
    }

    private void handleCreateAccount() {
        System.out.println("Enter your personal ID number");
        long userId = scan.nextLong();
        scan.nextLine();
        System.out.println("Enter your full name");
        String name = scan.nextLine();
        Nordea.addAccount(name, userId);
    }

    private void handleRemoveAccount() {
        System.out.println("Enter your account number");
        int accountNumber = scan.nextInt();
        if (Nordea.removeAccount(accountNumber)) {
            System.out.println("Account removed");
        } else {
            System.out.println("Account does not exist");
        }
    }

    private void handlePrintAllAccounts() {
        System.out.println(Nordea.getAllAccounts());
    }

    private void handleDeposit() {
        try {
            System.out.println("Enter account number:");
            int accountNumber = scan.nextInt();
            double amount = getPositiveAmount("Enter amount to deposit:");

            BankAccount account = Nordea.findByAccountNumber(accountNumber);
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
}

public ArrayList<BankAccount> getAllAccounts() {
    AllAccounts.sort((a, b) -> a.getHolder().getName().compareToIgnoreCase(b.getHolder().getName()));
    return AllAccounts;
}

@Test
public void testDeposit() {
    BankAccount account = new BankAccount("John Doe", 123456789L);
    account.deposit(100.0);
    assertEquals(100.0, account.getBalance(), 0.01);
}

public enum MenuOption {
    FIND_ACCOUNTS_FOR_HOLDER,
    FIND_BY_PART_OF_NAME,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    CREATE_ACCOUNT,
    REMOVE_ACCOUNT,
    PRINT_ALL_ACCOUNTS,
    EXIT
}
