package br.inatel.c07.lendfast.model;

public class Conta
{
    //membros
    private int codigo;
    private String senha;

    //construtor
    public Conta(int codigo, String senha)
    {
        this.codigo = codigo;
        this.senha = senha;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public String getSenha()
    {
        return senha;
    }
}

