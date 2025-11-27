package br.inatel.c07.lendfast.dao;

import java.sql.SQLException;

public class RelatorioDAO extends ConnectionDAO {

    // ==========================================================
    // PARTE 1: SELECTS SIMPLES (JOIN SEM TABELA INTERMEDIÁRIA)
    // ==========================================================

    /**
     * Relatório 1: Produtos e seus Donos
     * Tabelas: Produto (tem DAO) + Usuario (tem DAO)
     * Tipo: 1 para N (Um usuário tem vários produtos)
     */
    public void relatorioProdutosComDono() {
        connectToDb();
        System.out.println("\n--- RELATÓRIO: PRODUTOS E DONOS ---");
        // SELECT p.nome, u.nome FROM Produto p JOIN Locador l ... JOIN Usuario u ...
        // Mas no seu SQL o Produto já tem o CPF do Locador direto (locador_usuario_cpf)
        // Então podemos pular a tabela Locador e ir direto pro Usuario para pegar o nome.
        String sql = "SELECT p.nome AS Produto, p.nicho, u.nome AS Dono " +
                "FROM Produto p " +
                "JOIN Usuario u ON p.locador_usuario_cpf = u.CPF";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Produto: " + rs.getString("Produto") +
                        " (" + rs.getString("nicho") + ") | Dono: " + rs.getString("Dono"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { if (st != null) st.close(); if (connection != null) connection.close(); } catch (SQLException e) {}
        }
    }

    /**
     * Relatório 2: Lista Telefônica
     * Tabelas: Telefone (tem DAO) + Usuario (tem DAO)
     */
    public void relatorioTelefonesUsuarios() {
        connectToDb();
        System.out.println("\n--- RELATÓRIO: LISTA TELEFÔNICA ---");
        String sql = "SELECT u.nome, t.telefone " +
                "FROM Telefone t " +
                "JOIN Usuario u ON t.usuario_CPF = u.CPF";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome") + " | Tel: " + rs.getString("telefone"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { if (st != null) st.close(); if (connection != null) connection.close(); } catch (SQLException e) {}
        }
    }

    /**
     * Relatório 3: Pagamentos de Alugueis
     * Tabelas: Pagamento (SEM DAO) + Aluguel (SEM DAO)
     * Aqui provamos que o Java consegue ler tabelas que não têm classe Java!
     */
    public void relatorioPagamentosAluguel() {
        connectToDb();
        System.out.println("\n--- RELATÓRIO: PAGAMENTOS ---");
        String sql = "SELECT p.valor, p.metodoDePagamento, a.contrato " +
                "FROM Pagamento p " +
                "JOIN Aluguel a ON p.aluguel_codigo = a.codigo";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Valor: R$" + rs.getDouble("valor") +
                        " (" + rs.getString("metodoDePagamento") + ") | Contrato: " + rs.getString("contrato"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { if (st != null) st.close(); if (connection != null) connection.close(); } catch (SQLException e) {}
        }
    }

    // ==========================================================
    // PARTE 2: SELECTS COMPLEXOS (JOIN COM TABELA INTERMEDIÁRIA)
    // ==========================================================

    /**
     * Relatório 4: Usuários e Chamados de Suporte
     * Tabelas: Usuario -> Usuario_has_Suporte -> Suporte
     * Esse é o clássico N para N com tabela intermediária.
     */
    public void relatorioUsuarioSuporte() {
        connectToDb();
        System.out.println("\n--- RELATÓRIO: SUPORTE AO USUÁRIO ---");
        String sql = "SELECT u.nome, s.status, s.protocolo " +
                "FROM Usuario u " +
                "JOIN Usuario_has_Suporte uhs ON u.CPF = uhs.Usuario_CPF " +
                "JOIN Suporte s ON uhs.Suporte_protocolo = s.protocolo";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Usuário: " + rs.getString("nome") +
                        " | Protocolo: " + rs.getInt("protocolo") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { if (st != null) st.close(); if (connection != null) connection.close(); } catch (SQLException e) {}
        }
    }

    /**
     * Relatório 5: Quem alugou o quê? (Aluguéis e Locatários)
     * Tabelas: Aluguel -> Aluguel_has_Locatorio -> Locatorio -> Usuario
     * Um JOIN monstro de 4 tabelas!
     */
    public void relatorioAluguelLocatario() {
        connectToDb();
        System.out.println("\n--- RELATÓRIO: QUEM ALUGOU O QUÊ ---");
        String sql = "SELECT a.codigo, u.nome AS Locatario, a.fase, p.nome AS Produto " +
                "FROM Aluguel a " +
                "JOIN Aluguel_has_Locatorio ahl ON a.codigo = ahl.Aluguel_codigo " +
                "JOIN Locatorio l ON ahl.Locatorio_Usuario_CPF = l.usuario_CPF " +
                "JOIN Usuario u ON l.usuario_CPF = u.CPF " +
                "JOIN Produto p ON a.produto_codigo = p.codigo";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Aluguel #" + rs.getInt("codigo") +
                        " | Locatário: " + rs.getString("Locatario") +
                        " | Produto: " + rs.getString("Produto") +
                        " | Fase: " + rs.getString("fase"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { if (st != null) st.close(); if (connection != null) connection.close(); } catch (SQLException e) {}
        }
    }

    /**
     * Relatório 6: Detalhe de Problemas (Mais Detalhado)
     * Tabelas: Usuario -> Usuario_has_Suporte -> Suporte -> TipoProblema
     * Junta dados do usuário até o detalhe técnico do problema.
     */
    public void relatorioProblemasDetalhados() {
        connectToDb();
        System.out.println("\n--- RELATÓRIO: DETALHE DE PROBLEMAS TÉCNICOS ---");
        String sql = "SELECT u.nome, tp.setor, s.status " +
                "FROM Usuario u " +
                "JOIN Usuario_has_Suporte uhs ON u.CPF = uhs.Usuario_CPF " +
                "JOIN Suporte s ON uhs.Suporte_protocolo = s.protocolo " +
                "JOIN TipoProblema tp ON s.protocolo = tp.suporte_protocolo";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Cliente: " + rs.getString("nome") +
                        " | Setor Responsável: " + rs.getString("setor") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { if (st != null) st.close(); if (connection != null) connection.close(); } catch (SQLException e) {}
        }
    }
}