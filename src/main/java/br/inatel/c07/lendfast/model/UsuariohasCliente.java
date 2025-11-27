package br.inatel.c07.lendfast.model;

public class UsuariohasCliente
{
    //membros
    private char Usuario_CPF;
    private int suporte_protocolo;

    //construtor
    public UsuariohasCliente(char usuario_CPF, int suporte_protocolo)
    {
        Usuario_CPF = usuario_CPF;
        this.suporte_protocolo = suporte_protocolo;
    }
}
