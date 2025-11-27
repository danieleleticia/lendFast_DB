package br.inatel.c07.lendfast.dao;

import br.inatel.c07.lendfast.model.Conta;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContaDAO extends ConnectionDAO
{
    public boolean insertConta(Conta conta)
    {
        connectToDb(); //abre conexão

        //Comando Sql, igual no workbench
        String sql = "INSERT INTO Conta(codigo,senha) VALUES (?, ?)";

        try
        {
            pst = connection.prepareStatement(sql); //prepara comando

            //setando parametros
            pst.setInt(1,conta.getCodigo());
            pst.setString(2,conta.getSenha());
            pst.execute(); //executar insert
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Erro ao inserir conta: " + e.getMessage());
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
    public boolean updateConta(Conta conta)
    {
        connectToDb();
        String sql = "UPDATE Conta SET senha=? WHERE codigo=?";

        try
        {
            pst = connection.prepareStatement(sql); //prepara comando

            //setando parametros
            pst.setString(1,conta.getSenha());
            pst.setInt(2,conta.getCodigo());
            pst.execute(); //executar insert
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
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
    public boolean deleteConta(int codigo)
    {
        connectToDb();
        String sql = "DELETE FROM Conta WHERE codigo=?";

        try
        {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao deletar conta: " + e.getMessage());
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
    public ArrayList<Conta> selectContas()
    {
        ArrayList<Conta> contas = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Conta";

        try
        {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next())
            {
                Conta contaAux = new Conta(
                        rs.getInt("codigo"),
                        rs.getString("senha")
                );
                contas.add(contaAux);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao buscar contas: " + e.getMessage());
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
        return contas;
    }

}
