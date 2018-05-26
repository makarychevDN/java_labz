import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Date;

public final class Customer {
    private static final LocalDate EMPTY_AGE = LocalDate.now();
    private final String firstName;
    private final String secondName;
    private final LocalDate birthDate;
    private final Address address;
    private final static int AGE_OF_NOT_MATURE_CUSTOMER = 14;
    private final static int AGE_OF_MATURE_CUSTOMER = 21;
    public final static Customer MATURE_UNKNOWN_CUSTOMER = new Customer( LocalDate.of(LocalDate.now().getYear() - AGE_OF_MATURE_CUSTOMER, 1,1));
    public final static Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.of( LocalDate.now().getYear() - AGE_OF_NOT_MATURE_CUSTOMER, 1,1));

    public Customer(String firstName, String secondName, LocalDate birthDate, Address address){
        if(birthDate.isAfter(LocalDate.now()))
            throw new java.lang.IllegalArgumentException("о вы из будущего");
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public  Customer(){
        this("","",EMPTY_AGE,Address.EMPTY_ADDRESS);
    }

    public Customer(LocalDate birthDate){
        this("","", birthDate, Address.EMPTY_ADDRESS);
    }

    public int getAge() {
        return  Period.between(LocalDate.now(),birthDate).getYears();
    }

    public static int getAgeOfMatureCustomer() {
        return AGE_OF_MATURE_CUSTOMER;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ");
        if(secondName != null) sb.append(secondName).append(" ");
        if(firstName != null) sb.append(firstName).append(", ");
        if(birthDate != EMPTY_AGE) sb.append(birthDate).append(", ");
        if(!address.equals(Address.EMPTY_ADDRESS)) sb.append(address.toString());
        return sb.toString();
    }

    @Override
    public boolean equals(final Object obj1){
        if(obj1 instanceof Customer){
            Customer obj = (Customer)obj1;
            if(this.firstName != null && obj.firstName != null && this.firstName.equals(obj.firstName)
                && this.secondName != null && obj.secondName != null && this.secondName.equals(obj.secondName)
                && this.birthDate.equals(obj.birthDate)
                && this.address.equals(obj1))
                return true;
            else
                return false;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        return firstName.hashCode() ^ secondName.hashCode() ^ address.toString().hashCode() ^ birthDate.getYear();
    }
}
