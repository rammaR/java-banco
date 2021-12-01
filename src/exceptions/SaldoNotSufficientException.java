package exceptions;

public class SaldoNotSufficientException extends Exception {
    public SaldoNotSufficientException(double saldo, double value) {
        super("O saque no valor de "+value+" excede o saldo de "+saldo);
    }
}
