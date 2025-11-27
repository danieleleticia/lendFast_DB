package br.inatel.c07.lendfast.model;

public class Aluguel
{
    //membros
    private int codigo;
    private String fase;
    private String contrato;
    private int periodoAluguel;
    private int produto_codigo;
    private char produto_locador_usuario_cpf;

    //construtores
    public Aluguel(int codigo, String fase, String contrato, int periodoAluguel, int produto_codigo, char produto_locador_usuario_cpf)
    {
        this.codigo = codigo;
        this.fase = fase;
        this.contrato = contrato;
        this.periodoAluguel = periodoAluguel;
        this.produto_codigo = produto_codigo;
        this.produto_locador_usuario_cpf = produto_locador_usuario_cpf;
    }
}
