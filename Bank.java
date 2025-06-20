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
            .filter(holder -> holder != null && holder.getIdNr() == idNr) // Added null check
            .findFirst()
            .orElse(null);
    }

    public boolean removeAccount(int number) {
        for (int i = 0; i < AllAccounts.size(); i++) {
            if (AllAccounts.get(i).getAccountNumber() == number) {
                AllAccounts.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<BankAccount> getAllAccounts() {
        ArrayList<BankAccount> sortedAccounts = new ArrayList<>(AllAccounts); // Create a copy
        sortedAccounts.sort((a, b) -> a.getHolder().getName().compareToIgnoreCase(b.getHolder().getName()));
        return sortedAccounts;
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
