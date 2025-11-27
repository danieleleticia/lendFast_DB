package br.inatel.c07.lendfast.model;

public class AluguelhasLocatario
{
    //membros
    private int Aluguel_codigo;
    private char Locatario_Usuario_CPF;

    //construtor
    public AluguelhasLocatario(int aluguel_codigo, char locatario_Usuario_CPF)
    {
        Aluguel_codigo = aluguel_codigo;
        Locatario_Usuario_CPF = locatario_Usuario_CPF;
    }
}
