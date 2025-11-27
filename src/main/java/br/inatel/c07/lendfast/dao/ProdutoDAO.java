package br.inatel.c07.lendfast.dao;

import br.inatel.c07.lendfast.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO extends ConnectionDAO {

    // INSERT: Cadastrar um novo produto
    public boolean insertProduto(Produto produto) {
        connectToDb();
        // SQL batendo com seu script
        String sql = "INSERT INTO Produto (codigo, nome, nicho, descricao, locador_usuario_cpf) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, produto.getCodigo());
            pst.setString(2, produto.getNome());
            pst.setString(3, produto.getNicho());
            pst.setString(4, produto.getDescricao());
            // Aqui usamos o nome exato que você criou no Model
            pst.setString(5, produto.getLocador_usuario_cpf());

            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
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

    // SELECT: Listar todos os produtos
    public ArrayList<Produto> selectProdutos() {
        ArrayList<Produto> lista = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Produto";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Produto prod = new Produto(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("nicho"),
                        rs.getString("descricao"),
                        rs.getString("locador_usuario_cpf")
                );
                lista.add(prod);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
        } finally {
            try {
                if (st != null) st.close();
                if (rs != null) rs.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return lista;
    }

    // DELETE: Apagar produto pelo código
    public boolean deleteProduto(int codigo) {
        connectToDb();
        String sql = "DELETE FROM Produto WHERE codigo=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
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

    // UPDATE: Atualizar a descrição do produto
    public boolean updateProduto(int codigo, String novaDescricao) {
        connectToDb();
        String sql = "UPDATE Produto SET descricao=? WHERE codigo=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novaDescricao);
            pst.setInt(2, codigo);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
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