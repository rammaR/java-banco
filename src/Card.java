/**
 * @author Ramar N F
 */
public abstract class Card {

    protected Double numero;
    protected short validadeMes;
    protected short validadeAno;
    protected short numeroSeguranca;
    protected AbstractBanco banco;
    protected Cliente cliente;
    protected Conta contaCorrente;
    protected Conta contaPoupanca;

    public Double getNumero() {
        return numero;
    }

    public void setNumero(Double numero) {
        this.numero = numero;
    }

    public short getValidadeMes() {
        return validadeMes;
    }

    public void setValidadeMes(short validadeMes) {
        this.validadeMes = validadeMes;
    }

    public short getValidadeAno() {
        return validadeAno;
    }

    public void setValidadeAno(short validadeAno) {
        this.validadeAno = validadeAno;
    }

    public short getNumeroSeguranca() {
        return numeroSeguranca;
    }

    public void setNumeroSeguranca(short numeroSeguranca) {
        this.numeroSeguranca = numeroSeguranca;
    }

    public AbstractBanco getBanco() {
        return banco;
    }

    public void setBanco(AbstractBanco banco) {
        this.banco = banco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
}
