import exceptions.DigitalIncompatibleException;
import exceptions.SenhaNotMatchedException;

public class BancoDigital extends AbstractBanco implements IBanco {

    BancoDigital(String nome) {
        super(nome);
    }

    @Override
    protected void template_get_new_account(Cliente cliente) {
        cliente.setDigital(scanDigital());
    }

    @Override
    public boolean verificarSenha(Cliente cliente, Integer senha) throws SenhaNotMatchedException, DigitalIncompatibleException {
        if (!(cliente.getSenha().equals(senha))) {
            throw new SenhaNotMatchedException();
        }

        verificarDigital(cliente);

        return true;
    }

    private boolean verificarDigital(Cliente cliente) throws DigitalIncompatibleException {
        if (!(cliente.getDigital().equals(scanDigital()))) {
            throw new DigitalIncompatibleException(cliente.getNome());
        }

        return true;
    }

    private Integer scanDigital() {
        return "Mock Digital Scanner".hashCode();
    }
}
