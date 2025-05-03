public class Customer {
    private long idNr;
    private long customerNr; // Changed to instance variable
    private String name;
    private static long nextCustomerNr = 1; // Tracks the next available customer number

    public Customer(String name, long idNr) {
        this.name = name;
        this.idNr = idNr;
        this.customerNr = nextCustomerNr++; // Assign and increment
    }

    public String getName() {
        return name;
    }

    public long getIdNr() {
        return idNr;
    }

    public long getCustomerNr() { // Changed return type to long
        return customerNr;
    }

    @Override
    public String toString() {
        return String.format("Customer #%d | Name: %s | ID: %d", customerNr, name, idNr);
    }
}
