package org.example;

import java.util.*;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        System.out.println("Seja bem-vindo ao sistema de gestão de biblioteca!");
        while (true) {
            System.out.println("1 - Gerenciar Livros");
            System.out.println("2 - Gerenciar Membros");
            System.out.println("3 - Empréstimos e Devoluções");
            System.out.println("4 - Encerrar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1: gerenciarLivros(); break;
                case 2: gerenciarMembros(); break;
                case 3: gerenciarEmprestimos(); break;
                case 4: System.out.println("Sistema encerrado. Obrigado por visitar a nossa biblioteca!"); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void gerenciarLivros() {
        while (true) {
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Remover Livro");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1) {
                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                System.out.print("ISBN: ");
                String isbn = scanner.nextLine();
                biblioteca.adicionarLivro(new Livro(titulo, autor, isbn));
            } else if (opcao == 2) {
                System.out.print("ISBN do livro para remover: ");
                biblioteca.removerLivro(scanner.nextLine());
            } else if (opcao == 3) {
                biblioteca.listarLivros();
            } else break;
        }
    }

    private static void gerenciarMembros() {
        while (true) {
            System.out.println("1 - Registrar Membro");
            System.out.println("2 - Listar Membros");
            System.out.println("3 - Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                if (biblioteca.existeMembro(id)) {
                    System.out.println("Erro: Já existe um membro com esse ID.");
                    continue; // Volta para o menu de membros
                }

                System.out.print("Email: ");
                String email = scanner.nextLine();

                Membro novoMembro = new Membro(nome, id, email);
                biblioteca.registrarMembro(novoMembro);
            } else if (opcao == 2) {
                biblioteca.listarMembros();
            } else break;
        }
    }

    private static void gerenciarEmprestimos() {
        while (true) {
            System.out.println("1 - Registrar Empréstimo");
            System.out.println("2 - Devolver Livro");
            System.out.println("3 - Listar Empréstimos");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            if (opcao == 1) {
                System.out.print("ISBN do Livro: ");
                String isbn = scanner.nextLine();
                System.out.print("ID do Membro: ");
                int id = scanner.nextInt();
                biblioteca.registrarEmprestimo(isbn, id);
            } else if (opcao == 2) {
                System.out.print("ISBN do Livro: ");
                String isbn = scanner.nextLine();
                System.out.print("ID do Membro: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                biblioteca.devolverLivro(isbn, id);
            } else if (opcao == 3) {
                biblioteca.listarEmprestimos();
            } else {
                break;
            }
        }
    }
}
