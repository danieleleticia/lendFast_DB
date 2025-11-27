package br.inatel.c07.lendfast.model;

import java.math.BigDecimal;
import java.time.LocalDate;

// "extends Usuario": O Locador ganha todos os campos de Usuario automaticamente!
public class Locador extends Usuario {

    private BigDecimal reputacao;

    // O construtor recebe TUDO (dados do usuário + a reputação)
    public Locador(String cpf, String nome, LocalDate dataNasc, String endereco, int contaCodigo, BigDecimal reputacao) {
        // Manda os dados comuns para a classe pai (Usuario) cuidar
        super(cpf, nome, dataNasc, endereco, contaCodigo);
        this.reputacao = reputacao;
    }

    public BigDecimal getReputacao() {
        return reputacao;
    }

    public void setReputacao(BigDecimal reputacao) {
        this.reputacao = reputacao;
    }
}
