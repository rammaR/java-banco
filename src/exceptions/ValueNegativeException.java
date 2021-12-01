package exceptions;

public class ValueNegativeException extends Exception {
    public ValueNegativeException(double value) {
        super("O valor "+value+" não pode ser negativo");
    }
}
