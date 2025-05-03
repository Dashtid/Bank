import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> AllAccounts;

	public Bank() {
		AllAccounts = new ArrayList<BankAccount>();
	}

	public int addAccount(String holderName, long idNr) {
	    Customer holder = findHolder(idNr);
	    BankAccount account = (holder == null) 
	        ? new BankAccount(holderName, idNr) 
	        : new BankAccount(holder);
	    AllAccounts.add(account);
	    return account.getAccountNumber();
	}

	public Customer findHolder(long idNr) {
	    return AllAccounts.stream()
	        .map(BankAccount::getHolder)
	        .filter(holder -> holder.getIdNr() == idNr)
	        .findFirst()
	        .orElse(null);
	}

	public boolean removeAccount(int number) {
		for (int i = 0; i < AllAccounts.size(); i++) {                           // size börjar på 1 men array börjar på 0
			if (AllAccounts.get(i).getAccountNumber() == number) {
				AllAccounts.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList<BankAccount> getAllAccounts() {
		BankAccount a;
		BankAccount b;
		for (int i = 0; i < AllAccounts.size(); i++) {
			for (int j = 0; j < AllAccounts.size(); j++) {
            a = AllAccounts.get(i);
            b = AllAccounts.get(j);
            if (a.getHolder().getName().compareTo(b.getHolder().getName())> 0){
            	AllAccounts.set(i,AllAccounts.get(j));
            	AllAccounts.set(j,a);
            }
			}
		}
		return AllAccounts;
	}

	public BankAccount findByAccountNumber(int accountNumber) {
		for (int i = 0; i < AllAccounts.size(); i++) {
			if (AllAccounts.get(i).getAccountNumber() == accountNumber) {
				return AllAccounts.get(i);
			}
		}
		return null;

	}

	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> foundAccounts = new ArrayList<BankAccount>();
		for (int i = 0; i < AllAccounts.size(); i++) {
			if (AllAccounts.get(i).getHolder().getIdNr() == idNr) {
				foundAccounts.add(AllAccounts.get(i));
			}
		}
		return foundAccounts;
	}

	public ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> foundCustomers = new ArrayList<Customer>();
		for (int i = 0; i < AllAccounts.size(); i++) {
			if (AllAccounts.get(i).getHolder().getName().toUpperCase().contains(namePart.toUpperCase())) {
				foundCustomers.add(AllAccounts.get(i).getHolder());
			}
		}
		return foundCustomers;
	}
}
