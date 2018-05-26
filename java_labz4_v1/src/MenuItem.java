abstract public class MenuItem {
    protected static final int FREE_DISH_COST = 0;
    protected int cost;
    protected String name, description;

    protected MenuItem(String name){
        this(name,"", FREE_DISH_COST);
    }

    protected MenuItem(String name, String description){
        this(name,description,FREE_DISH_COST);
    }

    protected MenuItem(String name, String description, int cost){
        if(cost < 0)
            throw new java.lang.IllegalArgumentException("цена ниже нуля");
        this.cost = cost;
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return String.format("%s, %dp.", name, cost);
    }

    @Override
    public boolean equals(Object obj1){
        if(obj1 instanceof MenuItem){
            MenuItem obj = (MenuItem)obj1;
            if(obj.cost == this.cost && obj.name.equals(this.name))
                return true;
            else
                return false;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        return cost ^ name.hashCode() ^description.hashCode();
    }
}
