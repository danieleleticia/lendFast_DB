package br.inatel.c07.lendfast.dao;

import br.inatel.c07.lendfast.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

public class UsuarioDAO extends ConnectionDAO
{
    public boolean insertUsuario(Usuario usuario)
    {
        connectToDb(); //abre conexão

        //Comando Sql, igual no workbench
        String sql = "INSERT INTO Usuario(CPF,nome,dataNasc,endereco,cod_conta) VALUES (?, ?, ?, ?, ?)";

        try
        {
            pst = connection.prepareStatement(sql); //prepara comando

            //setando parametros
            pst.setString(1,usuario.getCPF());
            pst.setString(2,usuario.getNome());
            pst.setObject(3,usuario.getDataNasc());
            pst.setString(4,usuario.getEndereco());
            pst.setInt(5,usuario.getCod_conta());
            pst.execute(); //executar insert
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            return false;
        }
        finally
        {
            try
            {
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            }
            catch(SQLException e)
            {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    public boolean updateUsuario(Usuario usuario)
    {
        connectToDb();
        String sql = "UPDATE Usuario SET nome=?, dataNasc=?, endereco=?, cod_conta=? WHERE CPF=?";

        try
        {
            pst = connection.prepareStatement(sql); //prepara comando

            //setando parametros
            pst.setString(1,usuario.getNome());
            pst.setObject(2,usuario.getDataNasc());
            pst.setString(3,usuario.getEndereco());
            pst.setInt(4,usuario.getCod_conta());
            pst.setString(5,usuario.getCPF());
            pst.execute(); //executar insert
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
        finally
        {
            try
            {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    public boolean deleteUsuario(String CPF)
    {
        connectToDb();
        String sql = "DELETE FROM Usuario WHERE CPF=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, CPF);
            pst.execute();
            return true;

        }
        catch (SQLException e)
        {
            System.out.println("Erro ao deletar Usuario: " + e.getMessage());
            return false;
        }
        finally
        {
            try
            {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    public ArrayList<Usuario> selectUsuarios()
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Usuario";

        try
        {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next())
            {
                // Criando o objeto com os dados que vieram do banco
                Usuario usuarioAux = new Usuario(
                        rs.getString("CPF"),
                        rs.getString("nome"),
                        rs.getObject("dataNasc", LocalDate.class), // Converte automatico
                        rs.getString("endereço"),
                        rs.getInt("Conta_codigo")
                );
                usuarios.add(usuarioAux);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao buscar Usuarios: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (st != null) st.close();
                if (rs != null) rs.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Erro ao fechar: " + e.getMessage());
            }
        }
        return usuarios;
    }
}
