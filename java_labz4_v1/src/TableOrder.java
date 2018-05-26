import exceptions.NegativeSizeException;
import exceptions.UnlawfulActionException;

import java.time.LocalDateTime;

public class TableOrder implements Order {
    private static final int DEFAULT_SIZE = 16;
    private int size;
    private MenuItem[] menuItems;
    private Customer customer;
    private LocalDateTime time;

    TableOrder(){
        this(DEFAULT_SIZE, Customer.NOT_MATURE_UNKNOWN_CUSTOMER);
    }

    TableOrder(int dishSize, Customer customer) {
        if(dishSize < 0) throw new NegativeSizeException("Отрицательное значение массива");
        this.customer = customer;
        menuItems = new MenuItem[dishSize];
        size = 0;
        time = LocalDateTime.now();
    }

    TableOrder(MenuItem[] dishesList, Customer customer) {
        this.customer = customer;
        menuItems = dishesList;
        int count = 0;
        for (int i = 0; i < menuItems.length - 1; i++) {
            if (menuItems[i] != null) count++;
        }
        size = count;
        time = LocalDateTime.now();
    }

    public LocalDateTime getTime(){
        return time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean remove(MenuItem item){
        boolean remove = false;
        int index = 0;
        MenuItem[] newMenuItems = new MenuItem[size - 1];
        for (MenuItem i: newMenuItems) {
            if(item.equals(i)){
                size--;
                index++;
                remove = true;
                System.arraycopy(menuItems, 0, newMenuItems, 0, index - 1);
                System.arraycopy(menuItems, index + 1, newMenuItems, index, newMenuItems.length - index);
                break;
            }
        }
        if(remove) menuItems = newMenuItems;
        return remove;
    }

    public int removeAll(MenuItem item) {
        MenuItem[] newMenuItems = new MenuItem[size];
        System.arraycopy(newMenuItems, 0, menuItems, 0, size);
        int index = 0;
        for (MenuItem i: newMenuItems) {
            if (item.equals(i)) {
                newMenuItems[index] = null;
            }
            index++;
        }
        index = 0;
        int count = 0;
        for (MenuItem i: newMenuItems) {
            if (newMenuItems[index] == null) {
                newMenuItems[index] = newMenuItems[index + 1];
                newMenuItems[index + 1] = null;
            }
            index++;
        }
        System.arraycopy(menuItems, 0, newMenuItems, 0, size - count);
        return count;
    }

    public int itemsQuantity(MenuItem item){
        int count = 0;
        for (MenuItem i: menuItems){
            if(item.equals(i)) count++;
        }
        return count;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("TableOrder: ").append(size);
        for (MenuItem i: menuItems) {
            sb.append("\n").append(i.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj){
        boolean equals = false;
        if(obj instanceof TableOrder){
            TableOrder obj1 = (TableOrder) obj;
            if(obj1.customer.equals(this.customer)
                    && obj1.size == this.size
                    && obj1.time.equals(this.time)){
                obj1.menuItems = obj1.sortedItemByCost();
                this.menuItems = this.sortedItemByCost();
                int index = 0;
                for (MenuItem i : obj1.menuItems) {
                    if (i.equals(this.menuItems[index])) {
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

    public boolean add(MenuItem menuItem) {
        if(menuItem instanceof Drink && (((Drink) menuItem).alcoholVol > 0) && ((time.getHour() >= 22) | customer.getAge() < 21))
            throw new UnlawfulActionException("Алкоголь продается только совершеннолетним до 22:00");
        size++;
        if (size >= menuItems.length) {
            MenuItem[] newMenuItems = new MenuItem[menuItems.length * 2];
            System.arraycopy(menuItems, 0, newMenuItems, 0, menuItems.length);
            newMenuItems[size] = menuItem;
            menuItems = newMenuItems;
        } else menuItems[size] = menuItem;
        return true;
    }

    public boolean remove(String dishName) {
        boolean result = false;
        MenuItem[] newMenuItems = new MenuItem[size - 1];
        for (int i = 0; i < size; i++) {
            if (menuItems[i] != null && menuItems[i].getName().equals(dishName)) {
                size--;
                result = true;
                System.arraycopy(menuItems, 0, newMenuItems, 0, i - 1); //todo arraycopy
                System.arraycopy(menuItems, i + 1, newMenuItems, i, newMenuItems.length - i);
                break;
            }
        }
        if (result)
            menuItems = newMenuItems;
        return result;
    }

    public int removeAll(String dishName) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i] != null && menuItems[i].getName().equals(dishName)) {
                menuItems[i] = null;
                count++;
            }
        }
        for (int i = 0; i < size; i++) {
            if (menuItems[i] == null) {
                menuItems[i] = menuItems[i + 1];
                menuItems[i + 1] = null;
            }
        }
        MenuItem[] newMenuItems = new MenuItem[size - count];
        System.arraycopy(menuItems, 0, newMenuItems, 0, size - count);
        menuItems = newMenuItems;
        return count;    }

    public int itemsQuantity() {//todo возврат size <DONE>
        return size;
    }

    public MenuItem[] getMenuItems() {
        MenuItem[] newMenuItems = new MenuItem[size];
        System.arraycopy(menuItems, 0, newMenuItems, 0, size);
        return newMenuItems;
    }

    public int costTotal() {
        int costTotal = 0;
        for (int i = 0; i < size; i++) {
            costTotal += menuItems[i].getCost();
        }
        return costTotal;
    }

    public int itemsQuantity(String dishName) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i] != null && menuItems[i].getName().equals(dishName))
                count++;
        }
        return count;
    }

    public String[] itemNames(){
        String[] result = new String[size];
        int count = 0;
        boolean duplicateFound = false;
        String testVar;
        for (int i = 0; i < size; i++) {
            testVar = menuItems[i].getName();
            for (int j = 0; j < size; j++) {
                if (result[j].equals(testVar))
                    duplicateFound = true;
                    count++;
                    break;
            }
            if(duplicateFound) {
                result[count] = testVar;
                duplicateFound = false;
            }
        }
        return result;
    }

    public MenuItem[] getItemInPriceRange(int maxPrice, int minPrice){ //доп задание
        MenuItem[] result = new MenuItem[size];
        for(int i = 0; i < size; i++){
            if(menuItems[i].getCost() < maxPrice && menuItems[i].getCost() > minPrice){
                result[i] = menuItems[i];
            }
        }
        return result;
    }

    public MenuItem[] sortedItemByCost() {
        MenuItem max;
        MenuItem[] sortedMenuItems = new MenuItem[size];

        System.arraycopy(menuItems, 0, sortedMenuItems, 0, size);
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
}
