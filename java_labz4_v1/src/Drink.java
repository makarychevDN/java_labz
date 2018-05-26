public class Drink extends MenuItem implements Alcoholable{

    double alcoholVol;
    DrinkTypeEnum type;
    static final double ALCOHOL_VOL_IN_NON_ALCOHOLIC = 0;

    public Drink(String name, DrinkTypeEnum type){
        super(name);
        this.type = type;
        this.alcoholVol = ALCOHOL_VOL_IN_NON_ALCOHOLIC;
    }

    public Drink(String name, String description, int cost, DrinkTypeEnum type){
        this(name, description, cost, type, ALCOHOL_VOL_IN_NON_ALCOHOLIC);
    }

    public Drink(String name, String description, int cost, DrinkTypeEnum type, double alcoholVol){
        super(name,description,cost);
        if(alcoholVol < 0 | alcoholVol > 100)
            throw new java.lang.IllegalArgumentException("некорректный процент алкоголя");

        this.type = type;
        this.alcoholVol = alcoholVol;
    }

    @Override
    public boolean isAlcoholicDrink() {
        if(alcoholVol > 0) return true;
        else return false;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }

    public DrinkTypeEnum getType(){
        return type;
    }

    @Override
    public String toString(){
        return String.format("Drink: %s, %d% %s", super.toString(), alcoholVol, description);
    }

    @Override
    public boolean equals(Object obj){
        if(super.equals(obj)){
            Drink obj1 = (Drink)obj;
            if(obj1.type == this.type && obj1.alcoholVol == this.alcoholVol) return true;
            else return false;
        }
        else return false;
    }

    @Override
    public  int hashCode(){
        return super.hashCode() ^ type.hashCode() ^ (int)alcoholVol;
    }
}
