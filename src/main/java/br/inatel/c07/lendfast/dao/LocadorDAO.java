package br.inatel.c07.lendfast.dao;

import br.inatel.c07.lendfast.model.Locador;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocadorDAO extends ConnectionDAO {

    // INSERT: Promove um usuário existente a Locador
    public boolean insertLocador(Locador locador) {
        connectToDb();
        // Só inserimos na tabela Locador. Os dados pessoais (nome, endereço) já estão na tabela Usuario.
        String sql = "INSERT INTO Locador (usuario_CPF, reputacao) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            // 1. Pegamos o CPF que veio da herança (classe Usuario)
            pst.setString(1, locador.getCPF());
            // 2. BigDecimal garante a precisão do Decimal(3,2) do banco
            pst.setBigDecimal(2, locador.getReputacao());

            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir locador: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // UPDATE: Atualizar apenas a reputação
    public boolean updateReputacao(String cpf, double novaReputacao) {
        connectToDb();
        String sql = "UPDATE Locador SET reputacao=? WHERE usuario_CPF=?";

        try {
            pst = connection.prepareStatement(sql);
            // Convertendo double para BigDecimal na hora de salvar
            pst.setBigDecimal(1, java.math.BigDecimal.valueOf(novaReputacao));
            pst.setString(2, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar reputação: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // DELETE: Rebaixa o locador (ele volta a ser apenas usuário comum)
    public boolean deleteLocador(String cpf) {
        connectToDb();
        String sql = "DELETE FROM Locador WHERE usuario_CPF=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar locador: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    public ArrayList<Locador> selectLocadores() {
        ArrayList<Locador> lista = new ArrayList<>();
        connectToDb();

        // O COMANDO MÁGICO: JOIN
        // Estamos dizendo: "Traga os dados do Locador E do Usuário onde o CPF for igual"
        String sql = "SELECT * FROM Locador JOIN Usuario ON Locador.usuario_CPF = Usuario.CPF";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next())
            {
                Locador locadorAux = new Locador(
                        rs.getString("usuario_CPF"),              // CPF (Vem do Locador)
                        rs.getString("nome"),                     // Nome (Vem do Usuário)
                        rs.getObject("dataNasc", java.time.LocalDate.class), // Data (Vem do Usuário)
                        rs.getString("endereço"),                 // Endereço (Vem do Usuário - Cuidado com o ç)
                        rs.getInt("Conta_codigo"),                // Conta (Vem do Usuário)
                        rs.getBigDecimal("reputacao")             // Reputação (Vem do Locador)
                );
                lista.add(locadorAux);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Erro ao buscar locadores: " + e.getMessage());
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
        return lista;
    }
}