package Restaurant;

public class Main {
    public static void main(String[] args) {
        // Instantiate and use the various components of the restaurant system
        // For example:
        RestaurantOrderManagement orderManagement = new RestaurantOrderManagement();
        orderManagement.takeOrder(new FoodItem("Pizza", 12.99), 2, "No onions");
        orderManagement.displayOrder();

        RestaurantTableManagement tableManagement = new RestaurantTableManagement();
        tableManagement.assignTable(1);
        tableManagement.displayTableStatus();

        RestaurantBilling billing = new RestaurantBilling();
        billing.generateInvoice();
    }
}
