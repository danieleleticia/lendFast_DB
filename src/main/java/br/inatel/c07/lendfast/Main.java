package br.inatel.c07.lendfast; // Ajuste para seu pacote principal

import br.inatel.c07.lendfast.dao.*;
import br.inatel.c07.lendfast.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Instanciando as 5 DAOs Obrigatórias + Relatórios
        ContaDAO contaDAO = new ContaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        LocadorDAO locadorDAO = new LocadorDAO();
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();

        boolean running = true;

        while (running) {
            System.out.println("\n--- LENDFAST SYSTEM ---");
            System.out.println("1. Gerenciar Contas");
            System.out.println("2. Gerenciar Usuários");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("4. Gerenciar Locadores (Promoção de Usuário)");
            System.out.println("5. Gerenciar Telefones");
            System.out.println("6. Relatórios (Requisito Joins)");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            int op = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            switch (op) {
                case 1: // CRUD CONTA
                    System.out.println("1. Inserir | 2. Listar | 3. Atualizar | 4. Deletar");
                    int op1 = sc.nextInt();
                    sc.nextLine();
                    if(op1 == 1) {
                        System.out.print("Código da Conta: ");
                        int cod = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Senha: ");
                        String senha = sc.nextLine();
                        contaDAO.insertConta(new Conta(cod, senha));
                    } else if (op1 == 2) {
                        ArrayList<Conta> contas = contaDAO.selectContas();
                        for(Conta c : contas) System.out.println("ID: " + c.getCodigo() + " | Senha: " + c.getSenha());
                    } else if (op1 == 3) {
                        System.out.print("ID da Conta para atualizar: ");
                        int cod = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova Senha: ");
                        String senha = sc.nextLine();
                        contaDAO.updateConta(new Conta(cod, senha));
                    } else if (op1 == 4) {
                        System.out.print("ID da Conta para deletar: ");
                        int cod = sc.nextInt();
                        contaDAO.deleteConta(cod);
                    }
                    break;

                case 2: // CRUD USUARIO
                    System.out.println("1. Inserir | 2. Listar | 3. Atualizar | 4. Deletar");
                    int op2 = sc.nextInt();
                    sc.nextLine();
                    if(op2 == 1) {
                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Data Nasc (AAAA-MM-DD): ");
                        String dataStr = sc.nextLine();
                        LocalDate data = LocalDate.parse(dataStr);
                        System.out.print("Endereço: ");
                        String end = sc.nextLine();
                        System.out.print("Código da Conta Existente: ");
                        int codC = sc.nextInt();

                        usuarioDAO.insertUsuario(new Usuario(cpf, nome, data, end, codC));
                    } else if (op2 == 2) {
                        ArrayList<Usuario> users = usuarioDAO.selectUsuarios();
                        for(Usuario u : users) System.out.println("CPF: " + u.getCPF() + " | Nome: " + u.getNome());
                    } else if (op2 == 3) {
                        // Atualização simplificada para exemplo
                        System.out.print("CPF do Usuário: ");
                        String cpf = sc.nextLine();
                        System.out.print("Novo Nome: ");
                        String nome = sc.nextLine();
                        // Obs: Para ser rápido, vou manter os dados antigos fixos ou pedir de novo,
                        // aqui estou criando um dummy só com o nome novo para passar no DAO
                        // O ideal seria buscar o usuario antes, mas seu DAO pede um objeto completo.
                        System.out.println("Obs: Para atualizar, preencha tudo novamente.");
                        System.out.print("Data Nasc (AAAA-MM-DD): ");
                        LocalDate data = LocalDate.parse(sc.nextLine());
                        System.out.print("Endereço: ");
                        String end = sc.nextLine();
                        System.out.print("Cód Conta: ");
                        int cc = sc.nextInt();

                        usuarioDAO.updateUsuario(new Usuario(cpf, nome, data, end, cc));
                    } else if (op2 == 4) {
                        System.out.print("CPF para deletar: ");
                        String cpf = sc.nextLine();
                        usuarioDAO.deleteUsuario(cpf);
                    }
                    break;

                case 3: // CRUD PRODUTO
                    System.out.println("1. Inserir | 2. Listar | 3. Atualizar Descrição | 4. Deletar");
                    int op3 = sc.nextInt();
                    sc.nextLine();
                    if (op3 == 1) {
                        System.out.print("Código Produto: ");
                        int cod = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Nicho: ");
                        String nicho = sc.nextLine();
                        System.out.print("Descrição: ");
                        String desc = sc.nextLine();
                        System.out.print("CPF do Locador (Dono): ");
                        String cpfDono = sc.nextLine();
                        produtoDAO.insertProduto(new Produto(cod, nome, nicho, desc, cpfDono));
                    } else if (op3 == 2) {
                        ArrayList<Produto> prods = produtoDAO.selectProdutos();
                        for(Produto p : prods) System.out.println("Item: " + p.getNome() + " | Dono: " + p.getLocador_usuario_cpf());
                    } else if (op3 == 3) {
                        System.out.print("Cód Produto: ");
                        int cod = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nova Descrição: ");
                        String desc = sc.nextLine();
                        produtoDAO.updateProduto(cod, desc);
                    } else if (op3 == 4) {
                        System.out.print("Cód Produto: ");
                        int cod = sc.nextInt();
                        produtoDAO.deleteProduto(cod);
                    }
                    break;

                case 4: // CRUD LOCADOR (Especial pois usa Herança no select)
                    System.out.println("1. Promover Usuário a Locador | 2. Listar Locadores | 3. Deletar");
                    int op4 = sc.nextInt();
                    sc.nextLine();
                    if(op4 == 1) {
                        System.out.print("CPF do Usuário existente: ");
                        String cpf = sc.nextLine();
                        System.out.print("Reputação Inicial (ex: 5.0): ");
                        double rep = sc.nextDouble();
                        // Criamos um objeto Locador apenas com os dados necessários para o insertLocador
                        // (O DAO pega só o CPF e Reputação, o resto ele ignora no Insert específico do Locador)
                        Locador loc = new Locador(cpf, "", LocalDate.now(), "", 0, BigDecimal.valueOf(rep));
                        locadorDAO.insertLocador(loc);
                    } else if (op4 == 2) {
                        ArrayList<Locador> locs = locadorDAO.selectLocadores();
                        for (Locador l : locs) {
                            // Aqui vemos o JOIN funcionando: Dados do Locador + Dados do Usuário
                            System.out.println("Locador: " + l.getNome() + " | Reputação: " + l.getReputacao());
                        }
                    } else if (op4 == 3) {
                        System.out.print("CPF do Locador para rebaixar: ");
                        String cpf = sc.nextLine();
                        locadorDAO.deleteLocador(cpf);
                    }
                    break;

                case 5: // CRUD TELEFONE
                    System.out.println("1. Inserir | 2. Listar por CPF");
                    int op5 = sc.nextInt();
                    sc.nextLine();
                    if(op5 == 1) {
                        System.out.print("CPF do Usuário: ");
                        String cpf = sc.nextLine();
                        System.out.print("Número Telefone: ");
                        String num = sc.nextLine();
                        telefoneDAO.insertTelefone(new Telefone(cpf, num));
                    } else if (op5 == 2) {
                        System.out.print("CPF para buscar: ");
                        String cpf = sc.nextLine();
                        ArrayList<Telefone> tels = telefoneDAO.selectTelefonesPorCpf(cpf);
                        for(Telefone t : tels) System.out.println("Tel: " + t.getTelefone());
                    }
                    break;

                case 6: // RELATÓRIOS (Atende a exigência dos JOINS)
                    System.out.println("--- Relatórios Especiais ---");
                    System.out.println("1. Produtos e Donos (Sem Tabela Intermed.)");
                    System.out.println("2. Telefones e Nomes (Sem Tabela Intermed.)");
                    System.out.println("3. Pagamentos e Aluguel (Sem Tabela Intermed.)");
                    System.out.println("4. Usuários e Suporte (Com Tabela Intermed.)");
                    System.out.println("5. Alugueis e Locatários (Com Tabela Intermed.)");
                    System.out.println("6. Detalhe Problemas (Com Tabela Intermed.)");

                    int relOp = sc.nextInt();
                    if(relOp == 1) relatorioDAO.relatorioProdutosComDono();
                    if(relOp == 2) relatorioDAO.relatorioTelefonesUsuarios();
                    if(relOp == 3) relatorioDAO.relatorioPagamentosAluguel();
                    if(relOp == 4) relatorioDAO.relatorioUsuarioSuporte();
                    if(relOp == 5) relatorioDAO.relatorioAluguelLocatario();
                    if(relOp == 6) relatorioDAO.relatorioProblemasDetalhados();
                    break;

                case 0:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }
}