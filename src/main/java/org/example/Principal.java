package org.example;

import java.util.*;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();
    private static GerenciarLivros gerenciarLivros = new GerenciarLivros(biblioteca);
    private static GerenciarMembros gerenciarMembros = new GerenciarMembros(biblioteca);
    private static GerenciarEmprestimos gerenciarEmprestimos = new GerenciarEmprestimos(biblioteca);

    public static void main(String[] args) {
        System.out.println("Seja bem-vindo ao sistema de gestão de biblioteca!");
        while (true) {
            try {
                System.out.println("\n1 - Gerenciar Livros");
                System.out.println("2 - Gerenciar Membros");
                System.out.println("3 - Empréstimos e Devoluções");
                System.out.println("4 - Encerrar");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarLivros();
                    case 2 -> gerenciarMembros();
                    case 3 -> gerenciarEmprestimos();
                    case 4 -> {
                        System.out.println("\nSistema encerrado. Obrigado por visitar a nossa biblioteca!");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void gerenciarLivros() {
        while (true) {
            try{
                System.out.println("\n1 - Adicionar Livro");
                System.out.println("2 - Remover Livro");
                System.out.println("3 - Listar Livros");
                System.out.println("4 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarLivros.adicionarLivro();
                    case 2 -> gerenciarLivros.removerLivro();
                    case 3 -> gerenciarLivros.listarLivros();
                    case 4 -> { return; }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void gerenciarMembros() {
        while (true) {
            try{
                System.out.println("\n1 - Registrar Membro");
                System.out.println("2 - Listar Membros");
                System.out.println("3 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarMembros.registrarMembro();
                    case 2 -> gerenciarMembros.listarMembros();
                    case 3 -> { return; }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void gerenciarEmprestimos() {
        while (true) {
            try{
                System.out.println("\n1 - Registrar Empréstimo");
                System.out.println("2 - Devolver Livro");
                System.out.println("3 - Listar Empréstimos");
                System.out.println("4 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> gerenciarEmprestimos.registrarEmprestimo();
                    case 2 -> gerenciarEmprestimos.devolverLivro();
                    case 3 -> gerenciarEmprestimos.listarEmprestimos();
                    case 4 -> { return; }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}