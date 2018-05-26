public final class Address{
    private static final int DEFAULT_INT_VALUE = -1;
    private final String cityName;
    private final String streetName;
    private final int zipCode;
    private final int buildingNumber;
    private final char buildingLetter;
    private final int apartmentNumber;
    public static final Address EMPTY_ADDRESS = new Address();

    public Address(){
        this("","",DEFAULT_INT_VALUE,DEFAULT_INT_VALUE,' ',DEFAULT_INT_VALUE);
    }

    public Address(String  streetName, int buildingNumber, char buildingLetter, int apartmentNumber){
        this("Самара", streetName, DEFAULT_INT_VALUE, buildingNumber,buildingLetter,apartmentNumber);
    }

    public Address(String cityName, String streetName, int zipCode, int buildingNumber, char buildingLetter,int apartmentNumber){
        if(zipCode < 0 || buildingNumber < 0 || apartmentNumber < 0 || !Character.isLetter(buildingLetter))
            throw new java.lang.IllegalArgumentException("некорректные данные");
        this.cityName = cityName;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString(){
        return String.format("%s %d %s %s %s %d", cityName, zipCode,streetName,buildingNumber, buildingLetter,apartmentNumber);
    }

    @Override
    public boolean equals(final Object obj1){
        if(obj1 instanceof Address) { //проверяем является ли объект экземпляром Address
            Address obj = (Address)obj1;
            if (this.cityName != null && obj.cityName != null && this.cityName.equals(obj.cityName) //сравниваем все поля
                    && this.streetName != null && obj.streetName != null && this.streetName.equals(obj.streetName)
                    && this.zipCode == obj.zipCode
                    && this.buildingNumber == obj.buildingNumber
                    && this.buildingLetter == obj.buildingLetter
                    && this.apartmentNumber == obj.apartmentNumber)
                return  true;
            else
                return  false;

        }
        return false; // возвращаем false если объект не является экземпляром Address
    }

    @Override
    public int hashCode(){
        return streetName.hashCode() ^ cityName.hashCode() ^ zipCode ^ buildingNumber ^ apartmentNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }
}
