import exceptions.DigitalIncompatibleException;
import exceptions.SenhaNotMatchedException;

public interface IBanco {

    boolean verificarSenha(Cliente cliente, Integer senha) throws SenhaNotMatchedException, DigitalIncompatibleException;

}
