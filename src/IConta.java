import exceptions.SaldoNotSufficientException;
import exceptions.ValueNegativeException;

public interface IConta {
	
	void sacar(double valor) throws SaldoNotSufficientException, ValueNegativeException;
	
	void depositar(double valor) throws ValueNegativeException;
	
	void transferir(double valor, IConta contaDestino) throws SaldoNotSufficientException, ValueNegativeException;
	
	void imprimirExtrato();
}
