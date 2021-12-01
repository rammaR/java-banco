import exceptions.ClienteAlreadyHasAccountException;
import exceptions.ContaNotFoundException;

import java.util.*;

public abstract class AbstractBanco {

    protected String nome;
    protected Set<Conta> contas;
    protected Map<Cliente, List<Conta>> clienteContas;
    protected Map<Cliente, List<Card>> clienteCards;

    AbstractBanco(String nome){
        this.nome = nome;
        clienteCards = new HashMap<>();
        clienteContas = new HashMap<>();
        contas = new HashSet<>();
    }

    /**
     * @param cliente a ser adicionado ao banco
     * @return objeto cliente com informações preenchidas pela entidade Banco
     */
    public Card facade_new_account(Cliente cliente) throws ClienteAlreadyHasAccountException {
        this.adicionarCliente(cliente);

        Conta contaPoupanca = this.criarConta(cliente, TipoConta.CONTA_POUPANCA);
        cliente.setContaPoupanca(contaPoupanca);

        Conta contaCorrente = this.criarConta(cliente, TipoConta.CONTA_CORRENTE);
        cliente.setContaCorrente(contaCorrente);

        Card card = this.criarCartao(cliente, contaCorrente, contaPoupanca, this);

        this.template_get_new_account(cliente);
        
        return card;
    }

    protected abstract void template_get_new_account(Cliente cliente);

    public void adicionarCliente(Cliente cliente) throws ClienteAlreadyHasAccountException {
        if (clienteContas.entrySet().stream().filter(c -> c.equals(cliente)).findAny().isEmpty()) {
            clienteContas.put(cliente, new ArrayList<>());
            clienteCards.put(cliente, new ArrayList<>());
        } else {
            throw new ClienteAlreadyHasAccountException(cliente.getNome());
        }
    }

    public Conta criarConta(Cliente cliente, TipoConta tipoConta) {
        Conta conta = null;

        if (tipoConta.equals(TipoConta.CONTA_CORRENTE)) {
            conta = new ContaCorrente();
        } else {
            conta = new ContaPoupanca();
        }

        List<Conta> contas = this.clienteContas.get(cliente);
        contas.add(conta);
        this.clienteContas.put(cliente, contas);
        this.contas.add(conta);

        return conta;
    }

    public Card criarCartao(Cliente cliente, Conta contaCorrente, Conta contaPoupanca, AbstractBanco banco) {
        CartaoDebito card = null;

        card = new CartaoDebito();
        card.setBanco(banco);
        card.setCliente(cliente);
        card.setNumero(new Random().nextDouble());
        card.setNumeroSeguranca((short) new Random().nextInt(100));
        card.setValidadeAno((short) (Calendar.getInstance().get(Calendar.YEAR) + 4));
        card.setValidadeMes((short) Calendar.getInstance().get(Calendar.MONTH));
        card.setContaCorrente(contaCorrente);
        card.setContaPoupanca(contaPoupanca);

        List<Card> cards = clienteCards.get(cliente);
        cards.add(card);
        this.clienteCards.put(cliente, cards);

        return card;
    }

    /**
     * @return name of the bank
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome of the bank
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param agencia
     * @param numero
     * @return conta que mesma agencia e numero passados
     * @throws ContaNotFoundException
     */
    public IConta getContaByAgNumber(Integer agencia, Integer numero) throws ContaNotFoundException {
        return this.contas.stream().filter(c -> c.getAgencia() == agencia && c.getNumero() == numero).findFirst().orElseThrow(() -> new ContaNotFoundException(agencia, numero));
    }

}
