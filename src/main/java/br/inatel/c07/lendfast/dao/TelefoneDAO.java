package br.inatel.c07.lendfast.dao;

import br.inatel.c07.lendfast.model.Telefone;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefoneDAO extends ConnectionDAO {

    // INSERT: Adiciona um novo telefone para um usuário
    public boolean insertTelefone(Telefone telefone) {
        connectToDb();
        String sql = "INSERT INTO Telefone (telefone, usuario_CPF) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, telefone.getTelefone());
            pst.setString(2, telefone.getUsuario_CPF());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir telefone: " + e.getMessage());
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

    // SELECT ESPECÍFICO: Busca todos os telefones de UM CPF
    // Retorna uma Lista, pois a pessoa pode ter fixo, celular, comercial...
    public ArrayList<Telefone> selectTelefonesPorCpf(String cpfUsuario) {
        ArrayList<Telefone> listaTelefones = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Telefone WHERE usuario_CPF = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpfUsuario);
            rs = pst.executeQuery();

            while (rs.next()) {
                Telefone tel = new Telefone(
                        rs.getString("telefone"),
                        rs.getString("usuario_CPF")
                );
                listaTelefones.add(tel);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar telefones: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (rs != null) rs.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listaTelefones;
    }

    // UPDATE: Atualiza o número (Cuidado: Se tiver mais de um, isso pode atualizar todos se a PK for só o CPF)
    public boolean updateTelefone(String cpf, String novoTelefone) {
        connectToDb();
        String sql = "UPDATE Telefone SET telefone=? WHERE usuario_CPF=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novoTelefone);
            pst.setString(2, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
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

    // DELETE: Apaga os telefones de um usuário
    public boolean deleteTelefonesDoUsuario(String cpf) {
        connectToDb();
        String sql = "DELETE FROM Telefone WHERE usuario_CPF=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
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
}