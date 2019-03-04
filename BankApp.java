import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
	Bank Nordea = new Bank();
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		BankApp bankappen = new BankApp();
		bankappen.runApplication();

	}

	public void runApplication() {

		meny();
		menyVal();
	}

	public void meny() {
		System.out.println("Välkommen till banken!");
		System.out.println("1. Hitta konto utifrån innehavare");
		System.out.println("2. Sök kontoinnehavare utifrån (del av) namn");
		System.out.println("3. Sätt in");
		System.out.println("4. Ta ut");
		System.out.println("5. Överföring");
		System.out.println("6. Skapa konto");
		System.out.println("7. Ta bort konto");
		System.out.println("8. Skriv ut konton");
		System.out.println("9. Avsluta)");
		System.out.println("Skriv en siffra för att göra ett val");
	}

	public void menyVal() {
		ArrayList<BankAccount> Lista1 = new ArrayList<BankAccount>();
		ArrayList<Customer> Lista2 = new ArrayList<Customer>();
		int i = scan.nextInt();
		while (i < 1 || i > 9) {
			System.out.println("Välj en siffra inom 1-9!");
			menyVal();
		}
		switch (i) {
		case 1:
			i = 1;
			System.out.println("Skriv ID-nummer");
			long l = scan.nextLong();
			Lista1 = Nordea.findAccountsForHolder(l);
			System.out.println(Lista1);
			menyVal();
			break;

		case 2:
			String innehavare = scan.next();
			innehavare.trim();
			Lista2 = Nordea.findByPartofName(innehavare);
			System.out.println(Lista2);
			menyVal();
			break;

		case 3:
			System.out.println("Skriv in ert kontonummer");
			int kontonummer1 = scan.nextInt();
			System.out.println("Skriv in belopp som ni vill sätta in");
			int belopp1 = scan.nextInt();
			Nordea.findByAccountNumber(kontonummer1).deposit(belopp1);
			menyVal();
			break;

		case 4:
			System.out.println("Skriv in ert kontonummer");
			int kontonummer = scan.nextInt();
			System.out.println("Skriv in belopp som ni vill ta ut");
			int belopp = scan.nextInt();
			Nordea.findByAccountNumber(kontonummer).withdraw(belopp);
			menyVal();
			break;

		case 5:
			System.out.println("Skriv in ert kontonummer");
			int kontonummer2 = scan.nextInt();
			System.out.println("Skriv in kontonummer ni vill överföra till");
			int kontonummer3 = scan.nextInt();
			System.out.println("Skriv in belopp som ni överföra");
			int belopp2 = scan.nextInt();
			Nordea.findByAccountNumber(kontonummer2).withdraw(belopp2);
			Nordea.findByAccountNumber(kontonummer3).deposit(belopp2);
			menyVal();
			break;

		case 6:
			System.out.println("Skriv in erat personnummer");
			long användarId = scan.nextLong();
			scan.nextLine();
			System.out.println("Skriv in erat fullständiga namn");
			String namnet = scan.nextLine();
		//	namnet.trim();
			Nordea.addAccount(namnet, användarId);
			menyVal();
			break;

		case 7:
			System.out.println("Skriv in erat kontonummer");
			int kontonummer4 = scan.nextInt();
			if(Nordea.removeAccount(kontonummer4))
			{ System.out.println("Konto borttaget");}
			System.out.println("Konto finns inte");
			menyVal();
			break;

		case 8:
			System.out.println(Nordea.getAllAccounts());
			menyVal();
			break;

		case 9:
			break;

		}
	}
}
