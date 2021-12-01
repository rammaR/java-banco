import exceptions.ClienteAlreadyHasAccountException;
import exceptions.ContaNotFoundException;
import exceptions.SaldoNotSufficientException;
import exceptions.SenhaNotMatchedException;

public class Main {

	public static void main(String[] args) {
		AbstractBanco banco = new Banco("JAVA BANK");
		AbstractBanco bancoDigital = new BancoDigital("FUTURE BANK");

		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		venilton.setSenha(132);

		Cliente john = new Cliente();
		john.setNome("John Doe");
		john.setSenha(132);

		try {
			venilton.addCard( banco.facade_new_account(venilton) );
		} catch (ClienteAlreadyHasAccountException e) {
			e.printStackTrace();
		}

		venilton.imprimirInfosComuns();

		try {
			john.addCard( banco.facade_new_account(john) );
		} catch (ClienteAlreadyHasAccountException e) {
			e.printStackTrace();
		}

		john.imprimirInfosComuns();

		Terminal terminal = new Terminal();

		try {
            terminal.start(venilton.getCard(0));
        } catch (SaldoNotSufficientException | SenhaNotMatchedException | ContaNotFoundException e) {
			e.printStackTrace();
		}

		try {
			venilton.addCard( bancoDigital.facade_new_account(venilton) );
		} catch (ClienteAlreadyHasAccountException e) {
			e.printStackTrace();
		}

		try {
			terminal.start(venilton.getCard(1));
		} catch (SaldoNotSufficientException | SenhaNotMatchedException | ContaNotFoundException e) {
			e.printStackTrace();
		}
	}

}
