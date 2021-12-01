import exceptions.*;
//
import java.util.Scanner;

/**
 * @author Ramar N F
 * @see Banco
 * @see Conta
 * @see Cliente
 */
public class Terminal {

    private AbstractBanco banco;
    private Cliente cliente;
    private Card card;
    private Scanner scanner;

    /**
     * @param card
     * @throws SenhaNotMatchedException
     * @throws SaldoNotSufficientException
     */
    public void start(Card card) throws SenhaNotMatchedException, SaldoNotSufficientException, ContaNotFoundException {
        this.card = card;
        this.banco = card.getBanco();
        this.cliente = card.getCliente();
        this.ListarOperacoes();
    }

    /**
     * @throws SenhaNotMatchedException
     * @throws SaldoNotSufficientException
     */
    public void ListarOperacoes() throws ContaNotFoundException {
        System.out.println("###### " + this.banco.nome + "  ########");

        scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Imprimir saldo");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        this.sacar();
                    } catch (SaldoNotSufficientException | ValueNegativeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        this.depositar();
                    } catch (ValueNegativeException | SenhaNotMatchedException | DigitalIncompatibleException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        this.transferir();
                    } catch (SaldoNotSufficientException | ValueNegativeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    this.mostrarSaldo();
                    break;
                case 0:
                    this.clear();
                    break;
            }
        } while (opcao != 0);
    }

    private void mostrarSaldo() {
        Conta conta = escolhaConta();
        cliente.imprimirInfosComuns();
        conta.imprimirExtrato();
    }

    private Conta escolhaConta() {
        int opcao = 0;
        do {
            System.out.println("Escolha uma conta:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            opcao = scanner.nextInt();
        } while ((opcao < 1) || (opcao > 2));

        if (opcao == 1) {
            return this.card.getContaCorrente();
        } else {
            return this.card.getContaPoupanca();
        }
    }

    private void transferir() throws SaldoNotSufficientException, ContaNotFoundException, ValueNegativeException {
        Conta conta = this.card.getContaCorrente();

        System.out.println("Digite o valor para transferir: ");
        Double value = scanner.nextDouble();

        System.out.println("Digite agencia da conta destino: ");
        Integer agencia = scanner.nextInt();

        System.out.println("Digite numero da conta destino: ");
        Integer numero = scanner.nextInt();

        IConta contaDestino = this.banco.getContaByAgNumber(agencia, numero);
        //
        try {
            this.verificarSenha();
        } catch (DigitalIncompatibleException | SenhaNotMatchedException e) {
            e.printStackTrace();
            return;
        }
        //
        conta.transferir(value, contaDestino);

        System.out.println("Seu saldo agora é de: " + conta.getSaldo());
    }

    /**
     * @throws SenhaNotMatchedException
     */
    private void depositar() throws ValueNegativeException, SenhaNotMatchedException, DigitalIncompatibleException {
        Conta conta = escolhaConta();

        System.out.println("Digite o valor para depósito: ");
        Double valor = scanner.nextDouble();
        //
        this.verificarSenha();
        //
        conta.depositar(valor);
        //
        System.out.println("Seu saldo agora é de: " + conta.getSaldo());
    }

    private void clear() {
        this.banco = null;
        this.card = null;
        this.cliente = null;
    }

    /**
     * @throws SenhaNotMatchedException
     * @throws SaldoNotSufficientException
     */
    private void sacar() throws SaldoNotSufficientException, ValueNegativeException {
        Conta conta = escolhaConta();

        System.out.println("Digite o valor para saque: ");
        Double valor = scanner.nextDouble();
        //
        try {
            this.verificarSenha();
        } catch (DigitalIncompatibleException | SenhaNotMatchedException e) {
            System.out.println(e.getMessage());
            return;
        }
        //
        conta.sacar(valor);
        //
        System.out.println("Seu saldo agora é de: " + conta.getSaldo());
    }

    /**
     * @throws SenhaNotMatchedException
     */
    private void verificarSenha() throws SenhaNotMatchedException, DigitalIncompatibleException {
        System.out.println("Digite sua senha de 3 dígitos");
        Integer senha = scanner.nextInt();
        ((IBanco) this.banco).verificarSenha(this.cliente, senha);
    }

}
