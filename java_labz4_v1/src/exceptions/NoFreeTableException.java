package exceptions;

public class NoFreeTableException extends java.lang.NegativeArraySizeException {
    public NoFreeTableException(String message){
        super(message);
    }
}
