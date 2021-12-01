package exceptions;

public class ClienteAlreadyHasAccountException extends Exception {
    public ClienteAlreadyHasAccountException(String nome) {
        super("O cliente "+nome+" já possui conta aberta.");
    }
}
