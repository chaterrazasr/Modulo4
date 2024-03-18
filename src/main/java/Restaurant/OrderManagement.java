package Restaurant;

// Interface for order management
public interface OrderManagement {
    void takeOrder(MenuItem item, int quantity, String notes);
    void displayOrder();
}
