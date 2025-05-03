public class Customer {
    private long idNr;
    private static long customerNr = 0;
    private String name;

    public Customer(String name, long idNr) {
        this.name = name;
        this.idNr = idNr;
        customerNr++;
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

    @Override
    public String toString() {
        return String.format("Customer #%d | Name: %s | ID: %d", customerNr, name, idNr);
    }
}
