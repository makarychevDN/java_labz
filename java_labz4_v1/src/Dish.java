public final class Dish extends MenuItem{

    public Dish(String name, String description, int cost){
        super(name,description,cost);
    }

    public Dish(String name, String description){
        super(name,description);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", super.toString(), description);
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
