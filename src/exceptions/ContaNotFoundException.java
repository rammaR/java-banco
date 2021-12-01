package exceptions;

public class ContaNotFoundException extends Exception {
    public ContaNotFoundException(Integer agencia, Integer numero) {
        super("Conta não encontrada. Agencia "+agencia+" Número "+numero);
    }
}
