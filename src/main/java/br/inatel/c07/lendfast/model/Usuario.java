package br.inatel.c07.lendfast.model;

import java.time.LocalDate;

public class Usuario
{
    //membros
    private String CPF;
    private String nome;
    private LocalDate dataNasc;
    private String endereco;
    private int cod_conta;

    //construtor
    public Usuario(String CPF, String nome, LocalDate dataNasc, String endereco, int cod_conta)
    {
        this.CPF = CPF;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.cod_conta = cod_conta;
    }

    public String getCPF()
    {
        return CPF;
    }

    public String getNome()
    {
        return nome;
    }

    public LocalDate getDataNasc()
    {
        return dataNasc;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public int getCod_conta()
    {
        return cod_conta;
    }
}


