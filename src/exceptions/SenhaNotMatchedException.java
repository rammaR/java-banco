package exceptions;

public class SenhaNotMatchedException extends Exception {

    public SenhaNotMatchedException(){
        super("Senhas não coincidem");
    }

}
