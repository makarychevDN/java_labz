import exceptions.AlreadyAddedException;
import exceptions.NegativeSizeException;
import exceptions.NoFreeTableException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class TableOrderManager implements  OrderManager{
    private TableOrder[] tableOrders;

    TableOrderManager(int tableQuantity){
        if(tableQuantity < 0) throw new NegativeSizeException("отрицательный размер массива");
        tableOrders = new TableOrder[tableQuantity];
    }

    public TableOrder getOrder(int tableNumber){
        if (tableOrders[tableNumber] != null){
            return tableOrders[tableNumber];
        }
        else return null;
    }

    public void add(TableOrder tableOrder, int tableNumber){
        if(tableOrders[tableNumber] != null) throw new AlreadyAddedException("столик уже занят");
        tableOrders[tableNumber] = tableOrder;
    }

    public void addItem(MenuItem menuItem, int tableNumber){
        tableOrders[tableNumber].add(menuItem);
    }

    public void remove(int tableNumber){
        tableOrders[tableNumber] = null;
    }

    public ArrayList menuItems(Customer customer){
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        TableOrder tableOrder = new TableOrder();
        for (TableOrder i:tableOrders) {
            if(i.getCustomer().equals(customer)) tableOrder = i;
        }
        for(MenuItem i: tableOrder.getMenuItems()){
            menuItems.add(i);
        }
        return menuItems;
    }

    public int remove(Order order){
        int index = 0;
        for (TableOrder i: tableOrders) {
            if(tableOrders[index].equals(i)){
                tableOrders[index] = null;
                return index;
            }
            index++;
        }
        return -1;
    }

    public int removeAll(Order order){
        int removeAll = 0;
        int index = 0;
        for (TableOrder i: tableOrders) {
            if(tableOrders[index].equals(i)){
                tableOrders[index] = null;
                removeAll++;
            }
            index++;
        }
        return removeAll;
    }

    public int orderQuantity(){
        int orderQuantity = 0;
        for (TableOrder i: tableOrders) {
            if(i != null) orderQuantity++;
        }
        return orderQuantity;
    }

    public int orderQuantity(LocalDate date){
        int orderQuantity = 0;
        for (TableOrder i: tableOrders) {
            if(i != null && date.equals(i.getTime().toLocalDate())) orderQuantity++;
        }
        return orderQuantity;
    }

    public int[] freeTableNumbers(){
        int[] result = new int[tableOrders.length];
        for (int i = 0; i < tableOrders.length - 1; i++){
            if(isFree.test(tableOrders[i])) result[i] = i;
        }
        return result;
    }

    public int freeTableNumber(){
        int freeTableNumber = -1;
        for (int i = 0; i < tableOrders.length - 1; i++){
            if(isFree.test(tableOrders[i])) freeTableNumber = i;
        }
        if(freeTableNumber == -1)
            throw new NoFreeTableException("нет свободных столиков");
        return freeTableNumber;
    }

    public int[] busyTableNumbers(){
        int[] result = new int[tableOrders.length];
        for (int i = 0; i < tableOrders.length - 1; i++)
            if(!isFree.test(tableOrders[i])) result[i] = i;

        return result;
    }

    public static Predicate<TableOrder> isFree = new Predicate<TableOrder>() {
        @Override
        public boolean test(TableOrder tableOrder) {
            return tableOrder == null;
        }
    };

    public TableOrder[] getOrders()
    {
        int count = 0;
        for (int i = 0; i< tableOrders.length-1; i++){
            if (tableOrders[i]!= null){
                count++;
            }
        }
        TableOrder[] tableOrderList = new TableOrder[count];
        count = 0;
        for (int i = 0; i< tableOrders.length-1; i++){
            if (tableOrders[i]!= null){
                tableOrderList[count] = tableOrders[i];
                count++;
            }
        }
        return tableOrderList;
    }

    public int CostSummary(){
        int sum = 0;
        for (int i = 0; i < tableOrders.length-1; i++){
            if(tableOrders[i] != null)
            sum += tableOrders[i].costTotal();
        }
        return sum;
    }

    public int menuItemQuantity(String dishName){
        int sum = 0;
        for (int i = 0; i < tableOrders.length - 1; i++){
            if (tableOrders[i] != null)
            sum += tableOrders[i].itemsQuantity(dishName);
        }
        return sum;
    }

    public int menuItemQuantity(MenuItem item){
        int sum = 0;
        for (int i = 0; i < tableOrders.length - 1; i++){
            if (tableOrders[i] != null)
                sum += tableOrders[i].itemsQuantity(item);
        }
        return sum;
    }

    public int menuItemQuantity(){
        int itemQuantity = 0;
        for (TableOrder i: tableOrders) {
            if (i != null)
                itemQuantity += i.itemsQuantity();
        }
        return itemQuantity;
    }
}
