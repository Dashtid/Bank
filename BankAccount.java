import java.util.Scanner;
import java.util.logging.Logger;

public class BankAccount {
    private String holderName;
    private long holderId;
    private static int nextAccountNr = 1; // Tracks the next available account number
    private int accountNr; // Unique to each account
    private double balance;
    private Customer holder;
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());

    public BankAccount(String holderName, long holderId) {
        holder = new Customer(holderName, holderId);
        this.holderName = holderName;
        this.holderId = holderId;
        this.accountNr = nextAccountNr++; // Assign and increment
        balance = 0;
    }

    public BankAccount(Customer holder) {
        holderName = holder.getName();
        holderId = holder.getIdNr();
        this.holder = holder;
        this.accountNr = nextAccountNr++; // Assign and increment
        balance = 0;
    }

    public Customer getHolder() {
        return holder;
    }

    public int getAccountNumber() {
        return accountNr;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        logger.info("Deposited " + amount + " to account " + accountNr);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("Account #%d | Holder: %s | Balance: %.2f", accountNr, holderName, balance);
    }
}
