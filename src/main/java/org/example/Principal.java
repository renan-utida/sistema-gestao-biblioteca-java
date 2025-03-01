package org.example;

import java.util.*;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

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
                scanner.nextLine(); // Limpa o buffer para evitar loops infinitos
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }

    private static void gerenciarLivros() {
        while (true) {
            try {
                System.out.println("\n1 - Adicionar Livro");
                System.out.println("2 - Remover Livro");
                System.out.println("3 - Listar Livros");
                System.out.println("4 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        biblioteca.adicionarLivro(new Livro(titulo, autor, isbn));
                    }
                    case 2 -> {
                        System.out.print("ISBN do livro para remover: ");
                        biblioteca.removerLivro(scanner.nextLine());
                    }
                    case 3 -> biblioteca.listarLivros();
                    case 4 -> { return; }
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

    private static void gerenciarMembros() {
        while (true) {
            try {
                System.out.println("\n1 - Registrar Membro");
                System.out.println("2 - Listar Membros");
                System.out.println("3 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (biblioteca.existeMembro(id)) {
                            System.out.println("Erro: Já existe um membro com esse ID.");
                            continue;
                        }

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        biblioteca.registrarMembro(new Membro(nome, id, email));
                    }
                    case 2 -> biblioteca.listarMembros();
                    case 3 -> { return; }
                    default -> System.out.println("Opção inválida! Digite um número de 1 a 3.");
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

    private static void gerenciarEmprestimos() {
        while (true) {
            try {
                System.out.println("\n1 - Registrar Empréstimo");
                System.out.println("2 - Devolver Livro");
                System.out.println("3 - Listar Empréstimos");
                System.out.println("4 - Voltar");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("ISBN do Livro: ");
                        String isbn = scanner.nextLine();
                        System.out.print("ID do Membro: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        biblioteca.registrarEmprestimo(isbn, id);
                    }
                    case 2 -> {
                        System.out.print("ISBN do Livro: ");
                        String isbn = scanner.nextLine();
                        System.out.print("ID do Membro: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        biblioteca.devolverLivro(isbn, id);
                    }
                    case 3 -> biblioteca.listarEmprestimos();
                    case 4 -> { return; }
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
}
