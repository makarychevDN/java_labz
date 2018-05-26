import exceptions.UnlawfulActionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class InternetOrder implements Order {
    private LinkedList<MenuItem> menuItems = new LinkedList<>();
    private static final MenuItem[] DEFAULT_MENU_ITEMS = {};
    private int size;
    private Customer customer;
    private LocalDateTime time;

    InternetOrder(){
        this(DEFAULT_MENU_ITEMS);
    }

    InternetOrder(MenuItem[] menuItems){
        for (MenuItem i: menuItems) {
            this.menuItems.add(i);
        }
        time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean add(MenuItem menuItem){
        if(menuItem instanceof Drink && (((Drink) menuItem).alcoholVol > 0) && ((time.getHour() >= 22) | customer.getAge() < 21))
            throw new UnlawfulActionException("Алкоголь продается только совершеннолетним до 22:00");
        menuItems.add(menuItem);
        return true;
    }

    public boolean remove (String name){
        boolean remove = false;
        for (MenuItem i: menuItems) {
            if(name.equals(i.getName())) {
                menuItems.remove(i);
                remove = true;
                break;
            }
        }
        return remove;
    }

    public boolean remove(MenuItem menuItem){
        boolean remove = false;
        for (MenuItem i: menuItems) {
            if(menuItem.equals(i)) {
                menuItems.remove(i);
                remove = true;
                break;
            }
        }
        return remove;
    }

    public int removeAll(String name){
        int count = 0;
        for (MenuItem i: menuItems) {
            if(name.equals(i.getName())) {
                menuItems.remove(i);
                count++;
            }
        }
        return count;
    }

    public int removeAll(MenuItem menuItem){
        int count = 0;
        for (MenuItem i: menuItems) {
            if(menuItem.equals(i)) {
                menuItems.remove(i);
                count++;
            }
        }
        return count;
    }

    public int itemsQuantity(){
        return menuItems.size();
    }

    public int itemsQuantity(String name){
        int count = 0;
        for (MenuItem i: menuItems) {
            if(i.getName().equals(name)) count++;
        }
        return count;
    }

    public int itemsQuantity(MenuItem item){
        int count = 0;
        for (MenuItem i: menuItems) {
            if(i.equals(item)) count++;
        }
        return count;
    }

    public MenuItem[] getMenuItems() {
        int count = menuItems.size();
        for (MenuItem i: menuItems) {
            if(i == null) count--;
        }
        MenuItem[] getMenuItems = new MenuItem[count];
        count = 0;
        for (MenuItem i: menuItems) {
            getMenuItems[count] = i;
            count++;
        }
        return getMenuItems;
    }

    public int costTotal(){
        int costTotal = 0;
        for (MenuItem i: menuItems) {
            costTotal += i.getCost();
        }
        return costTotal;
    }

    public String[] itemNames(){
        String[] itemNames = new String[size];
        int count = 0;
        boolean duplicateIsFound = false;
        String testVar;
        for (MenuItem i: menuItems) {
            testVar = i.getName();
            for (String j: itemNames) {
                if(j.equals(testVar)) {
                    duplicateIsFound = true;
                    count++;
                    break;
                }
            }
            if(duplicateIsFound){
                itemNames[count] = testVar;
                duplicateIsFound = false;
            }
        }
        return itemNames;
    }

    public MenuItem[] sortedItemByCost() {
        MenuItem max;
        MenuItem[] sortedMenuItems = new MenuItem[size];
        System.arraycopy(menuItems, 0, sortedMenuItems, 0, size);
        int count = 0;
        for (MenuItem i: menuItems){
            sortedMenuItems[count] = i;
            count++;
        }
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (sortedMenuItems[i].getCost() < sortedMenuItems[j].getCost()) {
                    max = sortedMenuItems[j];
                    sortedMenuItems[j] = sortedMenuItems[i];
                    sortedMenuItems[i] = max;
                }
            }
        }
        return sortedMenuItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Internet Order:");
        for (MenuItem i:menuItems) {
            sb.append("\n").append(i.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj){
        boolean equals = false;
        if(obj instanceof InternetOrder){
            InternetOrder obj1 = (InternetOrder) obj;
            if(obj1.customer.equals(this.customer)
                    && obj1.size == this.size
                    && this.time.equals(obj1.time)){
                MenuItem[] obj1SortedItems = ((InternetOrder) obj).sortedItemByCost();
                MenuItem[] thisSortedItems = ((InternetOrder) obj).sortedItemByCost();
                int index = 0;
                for (MenuItem i:obj1SortedItems) {
                    if (i.equals(thisSortedItems[index])) {
                        equals = true;
                    } else break;
                }

            }
        }
        return equals;
    }

    @Override
    public int hashCode(){
        int hashCode;
        hashCode = size ^ customer.hashCode();
        for (MenuItem i: menuItems) {
            hashCode ^= i.hashCode();
        }
        return hashCode;
    }
}
