import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import exceptions.UnlawfulActionException;

public interface Order {
    boolean add(MenuItem menuItem) throws UnlawfulActionException;
    boolean remove(MenuItem menuItem);
    boolean remove(String name);
    int removeAll(String name);
    int removeAll(MenuItem menuItem);
    int itemsQuantity();
    int itemsQuantity(String name);
    int itemsQuantity(MenuItem menuItem);
    String[] itemNames();
    MenuItem[] getMenuItems();
    MenuItem[] sortedItemByCost();
    int costTotal();
    void setCustomer(Customer customer);
    Customer getCustomer();
    boolean equals(Object obj);
    String toString();
    int hashCode();

}
