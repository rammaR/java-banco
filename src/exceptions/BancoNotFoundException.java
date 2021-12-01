package exceptions;

/**
 * @author Ramar N F
 * @see Exception
 */
public class BancoNotFoundException extends Exception {
    public BancoNotFoundException() {
        super("Banco não encontrado.");
    }
}
