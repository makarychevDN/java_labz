import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class InternetOrderManager implements  OrderManager{
    private LinkedList<InternetOrder> internetOrders = new LinkedList<>();
    private static final InternetOrder[] DEFAULT_INTERNET_ORDERS = {};

    InternetOrderManager(InternetOrder[] internetOrders){
        for(InternetOrder i: internetOrders){
            this.internetOrders.add(i);
        }
    }

    InternetOrderManager(){
        this(DEFAULT_INTERNET_ORDERS);
    }

    public boolean add(InternetOrder order){
        internetOrders.add(order);
        return true;
    }

    public ArrayList menuItems(Customer customer){
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        InternetOrder order = new InternetOrder();
        for (InternetOrder i:internetOrders) {
            if(i.getCustomer().equals(customer)) order = i;
        }
        for(MenuItem i: order.getMenuItems()){
            menuItems.add(i);
        }
        return menuItems;
    }

    public InternetOrder getFirstOrder(){
        return internetOrders.getFirst();
    }

    public InternetOrder getAndRemoveFirstOrder(){
        InternetOrder getAndRemoveFirstOrder = internetOrders.getFirst();
        internetOrders.getFirst();
        return getAndRemoveFirstOrder;
    }

    public int CostSummary(){
        int costTotal = 0;
        for (InternetOrder i: internetOrders) {
            costTotal+= i.costTotal();
        }
        return costTotal;
    }

    public int orderQuantity(){
        return internetOrders.size();
    }

    public int orderQuantity(LocalDate date){
        int orderQuantity = 0;
        for (InternetOrder i: internetOrders) {
            if(i != null && date.equals(i.getTime().toLocalDate())) orderQuantity++;
        }
        return orderQuantity;
    }

    public InternetOrder[] getOrders() {
        InternetOrder[] Orders = new InternetOrder[internetOrders.size()];
        int index = 0;
        for (InternetOrder i: internetOrders) {
            Orders[index] = i;
        }
        return Orders;
    }

    public int menuItemQuantity(String name){
            int count = 0;
            for (InternetOrder i: internetOrders) {
                count += i.itemsQuantity(name);
            }
            return count;

    }

    public int menuItemQuantity(MenuItem item){
        int count = 0;
        for (InternetOrder i: internetOrders) {
            count += i.itemsQuantity(item);
        }
        return count;

    }
}
