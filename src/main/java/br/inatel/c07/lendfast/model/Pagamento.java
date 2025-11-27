package br.inatel.c07.lendfast.model;

public class Pagamento
{
    //membros
    private int id_pagamento;
    private String metodoDePagamento;
    private double valor;
    private String fase;
    private int aluguel_codigo;

    //construtor
    public Pagamento(int id_pagamento, String metodoDePagamento, double valor, String fase, int aluguel_codigo)
    {
        this.id_pagamento = id_pagamento;
        this.metodoDePagamento = metodoDePagamento;
        this.valor = valor;
        this.fase = fase;
        this.aluguel_codigo = aluguel_codigo;
    }


}
