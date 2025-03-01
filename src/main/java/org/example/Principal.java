package org.example;

import java.util.*;
import java.util.regex.*;

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
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
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
                        if (titulo.trim().isEmpty()) {
                            throw new IllegalArgumentException("Título não pode ser vazio.");
                        }

                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        if (autor.trim().isEmpty()) {
                            throw new IllegalArgumentException("Autor não pode ser vazio.");
                        } else if (!autor.matches("[a-zA-Z\\s]+")) {
                            throw new IllegalArgumentException("Autor deve conter apenas letras e espaços.");
                        }

                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        if (isbn.trim().isEmpty()) {
                            throw new IllegalArgumentException("ISBN não pode ser vazio.");
                        } else if (!isbn.matches("\\d+")) {
                            throw new IllegalArgumentException("ISBN deve conter apenas números.");
                        }

                        biblioteca.adicionarLivro(new Livro(titulo, autor, isbn));
                    }
                    case 2 -> {
                        System.out.print("ISBN do livro para remover: ");
                        String isbn = scanner.nextLine();
                        if (isbn.trim().isEmpty()) {
                            throw new IllegalArgumentException("ISBN não pode ser vazio.");
                        } else if (!isbn.matches("\\d+")) {
                            throw new IllegalArgumentException("ISBN deve conter apenas números.");
                        }
                        biblioteca.removerLivro(isbn);
                    }
                    case 3 -> biblioteca.listarLivros();
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
                        if (nome.trim().isEmpty()) {
                            throw new IllegalArgumentException("Nome não pode ser vazio.");
                        } else if (!nome.matches("[a-zA-Z\\s]+")) {
                            throw new IllegalArgumentException("Nome deve conter apenas letras e espaços.");
                        }

                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (biblioteca.existeMembro(id)) {
                            System.out.println("Erro: Já existe um membro com esse ID.");
                            continue;
                        }

                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        if (email.trim().isEmpty()) {
                            throw new IllegalArgumentException("Email não pode ser vazio.");
                        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                            throw new IllegalArgumentException("Email inválido.");
                        }

                        biblioteca.registrarMembro(new Membro(nome, id, email));
                    }
                    case 2 -> biblioteca.listarMembros();
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
                        if (isbn.trim().isEmpty()) {
                            throw new IllegalArgumentException("ISBN não pode ser vazio.");
                        } else if (!isbn.matches("\\d+")) {
                            throw new IllegalArgumentException("ISBN deve conter apenas números.");
                        }

                        System.out.print("ID do Membro: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        biblioteca.registrarEmprestimo(isbn, id);
                    }
                    case 2 -> {
                        System.out.print("ISBN do Livro: ");
                        String isbn = scanner.nextLine();
                        if (isbn.trim().isEmpty()) {
                            throw new IllegalArgumentException("ISBN não pode ser vazio.");
                        } else if (!isbn.matches("\\d+")) {
                            throw new IllegalArgumentException("ISBN deve conter apenas números.");
                        }

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
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
