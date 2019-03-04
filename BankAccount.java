
public class BankAccount {

	private String holderName;
	private long holderId;
	private static int accountNr = 0;
	private double balance;
	private Customer holder;

	public BankAccount(String holderName, long holderId) {
		holder = new Customer(holderName, holderId);
		this.holderName = holderName;
		this.holderId = holderId;
		accountNr = accountNr + 1;
		balance = 0;
	}

	public BankAccount(Customer holder) {
		holderName = holder.getName();
		holderId = holder.getIdNr();
		this.holder = holder;
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
		balance = balance + amount;
	}

	public void withdraw(double amount) {
		if (balance - amount >= 0) {
			balance = balance - amount;
		}
	}

	public String toString() {
		return holderId + "(" + holderName + ")" + accountNr + balance;
	}
}
