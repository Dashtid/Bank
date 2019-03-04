


public class Customer {
	private long idNr;
	private static long customerNr = 0;
	private String name;
	
	public Customer(String name, long idNr) {
		this.name = name;
		this.idNr = idNr;
		customerNr = customerNr + 1;
	}
	
	public String getName() {
		return name;
	}
	
	public long getIdNr() {
		return idNr;
	}
	
	public int getCustomerNr() {
		return (int) customerNr;
	}
	
	public String toString() {
		return idNr + "(" + name +")" + customerNr;
	}
	
	
}
