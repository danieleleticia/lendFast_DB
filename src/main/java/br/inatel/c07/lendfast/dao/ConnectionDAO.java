package br.inatel.c07.lendfast.dao;

import java.sql.*; //Importa Connection...

public abstract class ConnectionDAO
{
    Connection connection; //conexao com o banco

    //Parametros utilizados nas subclasses
    PreparedStatement pst; //Comando SQL com parametros
    Statement st; //Comando SQL simples (sem parametros)
    ResultSet rs; //Resultado das consultas SQL

    String database = "AV1"; //nome do BD
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/"+ database;

    //Estabelecer a conexão com o banco
    public Connection connectToDb()
    {
        try
        {
            connection = DriverManager.getConnection(url,user,password);
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }
}
