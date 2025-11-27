package br.inatel.c07.lendfast.model;

public class Produto
{
    //membros
    private int codigo;
    private String nome;
    private String nicho;
    private String descricao;
    private char locador_usuario_cpf;

    //construtor
    public Produto(int codigo, String nome, String nicho, String descricao, char locador_usuario_cpf)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.nicho = nicho;
        this.descricao = descricao;
        this.locador_usuario_cpf = locador_usuario_cpf;
    }
}
