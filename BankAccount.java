import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Logger;
public class BankAccount {
	private Scanner scan = new Scanner(System.in);
	private String holderName;
	private long holderId;
	private static int nextAccountNr = 1; // Tracks the next available account number
	private int accountNr; // Unique to each account
	private double balance;
	private Customer holder;
	private static ArrayList<BankAccount> AllAccounts = new ArrayList<>();
	private static final Logger logger = Logger.getLogger(BankAccount.class.getName());

	public BankAccount(String holderName, long holderId) {
		holder = new Customer(holderName, holderId);
		this.holderName = holderName;
		this.holderId = holderId;
		this.accountNr = nextAccountNr++; // Assign and increment
		balance = 0;
		AllAccounts.add(this);
	}

	public BankAccount(Customer holder) {
		holderName = holder.getName();
		holderId = holder.getIdNr();
		this.holder = holder;
		this.accountNr = nextAccountNr++; // Assign and increment
		balance = 0;
		AllAccounts.add(this);
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
		balance = balance + amount;
	}

	public void withdraw(double amount) {
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient balance.");
		}
		balance -= amount;
	}

	public String toString() {
		return holderId + "(" + holderName + ")" + accountNr + balance;
	}

	public static ArrayList<BankAccount> getAllAccounts() {
		Collections.sort(AllAccounts, (a, b) -> a.getHolder().getName().compareTo(b.getHolder().getName()));
		return AllAccounts;
	}

	public static BankAccount findByAccountNumber(int accountNumber) {
		for (BankAccount account : AllAccounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return null;
	}

	/**
	 * Adds a new account to the bank.
	 * @param holderName The name of the account holder.
	 * @param idNr The ID number of the account holder.
	 * @return The account number of the newly created account.
	 */
	public int addAccount(String holderName, long idNr) {
		int accountNumber = nextAccountNr++; // Existing logic
		logger.info("Account created: " + accountNumber + " for " + holderName);
		return accountNumber;
	}

	public void handleDeposit() {
		System.out.println("Enter account number:");
		int accountNumber = scan.nextInt();
		double amount = getPositiveAmount();
		BankAccount account = BankAccount.findByAccountNumber(accountNumber);
		if (account != null) {
			account.deposit(amount);
			System.out.println("Deposit successful.");
		} else {
			System.out.println("Account not found.");
		}
	}

	private double getPositiveAmount() {
		double amount;
		do {
			System.out.println("Enter a positive amount:");
			amount = scan.nextDouble();
		} while (amount <= 0);
		return amount;
	}
}
