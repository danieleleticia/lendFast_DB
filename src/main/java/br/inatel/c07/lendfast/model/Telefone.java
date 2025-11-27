package br.inatel.c07.lendfast.model;

public class Telefone
{
    //membros
    private String usuario_CPF;
    private String telefone;

    //construtor
    public Telefone(String usuario_CPF, String telefone)
    {
        this.usuario_CPF = usuario_CPF;
        this.telefone = telefone;
    }

    public String getUsuario_CPF()
    {
        return usuario_CPF;
    }

    public String getTelefone()
    {
        return telefone;
    }
}
