package br.inatel.c07.lendfast.model;

public class Produto
{
    //membros
    private int codigo;
    private String nome;
    private String nicho;
    private String descricao;
    private String locador_usuario_cpf;

    //construtor
    public Produto(int codigo, String nome, String nicho, String descricao, String locador_usuario_cpf)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.nicho = nicho;
        this.descricao = descricao;
        this.locador_usuario_cpf = locador_usuario_cpf;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public String getNicho()
    {
        return nicho;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public String getLocador_usuario_cpf()
    {
        return locador_usuario_cpf;
    }
}
