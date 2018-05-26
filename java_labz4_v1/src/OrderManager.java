import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface OrderManager {
    int orderQuantity();
    Order[] getOrders();
    int CostSummary();
    int menuItemQuantity(String name);
    int menuItemQuantity(MenuItem item);
    int orderQuantity(LocalDate date);
    ArrayList menuItems(Customer customer);
}
