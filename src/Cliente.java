import java.util.ArrayList;
import java.util.List;

/**
 * @author Venilton FalvoJr
 */
public class Cliente {

    private String nome;
    private Integer senha;
    private List<Card> cards;
    private Conta contaCorrente;
    private Conta contaPoupanca;
    private Integer digital;

    Cliente() {
        cards = new ArrayList<>();
    }

    /**
     * imprime resumo do cliente e suas contas
     */
    public void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.getNome()));
        this.contaCorrente.imprimirInfosComuns();
        this.contaPoupanca.imprimirInfosComuns();
    }

    public void addCard(Card card) {
        if (this.cards.stream().filter(c -> c.equals(card)).findAny().isEmpty()) {
            this.cards.add(card);
        }
    }

    public Card getCard(Integer index) {
        return this.cards.get(index);
    }

    public Integer getDigital() {
        return digital;
    }

    public void setDigital(Integer digital) {
        this.digital = digital;
    }

    public Conta getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(Conta contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public Conta getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(Conta contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
