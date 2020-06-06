package domain.models;

public class ShoppingCard {
    private long customer_id;
    private int balance;

    public ShoppingCard(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "customer_id=" + customer_id +
                ", balance=" + balance +
                '}';
    }
}
