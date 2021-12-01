import exceptions.SaldoNotSufficientException;
import exceptions.SenhaNotMatchedException;
import exceptions.ValueNegativeException;

/**
 * @author Venilton FalvoJr
 * @see IConta
 */
public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo = 0;

    public Conta() {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
    }

    public Conta(Integer agencia, Integer numero) {
        this.agencia = agencia;
        this.numero = numero;
    }

    /**
     * @param value to withdraw
     */
    @Override
    public void sacar(double value) throws SaldoNotSufficientException, ValueNegativeException {
        if(value <= 0){
            throw new ValueNegativeException(value);
        }

        if((this.saldo - value) < 0){
            throw new SaldoNotSufficientException(this.saldo, value);
        }

        saldo -= value;
    }

    /**
     * @param value to deposit
     */
    @Override
    public void depositar(double value) throws ValueNegativeException {
        if(value <= 0){
            throw new ValueNegativeException(value);
        }

        saldo += value;
    }

    /**
     * @param value          to transfer to destinyAccount
     * @param destinyAccount that will receive the value
     */
    @Override
    public void transferir(double value, IConta destinyAccount) throws SaldoNotSufficientException, ValueNegativeException {
        this.sacar(value);
        destinyAccount.depositar(value);
    }

    /**
     * @return agencia
     */
    public int getAgencia() {
        return agencia;
    }

    /**
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return saldo
     */
    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns(){
        System.out.println(String.format("Agencia: %d", this.getAgencia()));
        System.out.println(String.format("Numero: %d", this.getNumero()));
        System.out.println(String.format("Saldo: %.2f", this.getSaldo()));
    }

    @Override
    public boolean equals(Object obj) {
        return (this.agencia == ((Conta) obj).getAgencia() && ((Conta) obj).getNumero() == numero);
    }
}
