import exceptions.ClienteAlreadyHasAccountException;
import exceptions.ContaNotFoundException;
import exceptions.SenhaNotMatchedException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Venilton FalvoJr
 * @see Conta
 * @see Card
 */
public class Banco extends AbstractBanco implements IBanco {

    public Banco(String nome) {
        super(nome);
    }

    @Override
    protected void template_get_new_account(Cliente cliente) {
        return;
    }

    public boolean verificarSenha(Cliente cliente, Integer senha) throws SenhaNotMatchedException {
        if (!(cliente.getSenha().equals(senha))) {
            throw new SenhaNotMatchedException();
        }

        return true;
    }
}