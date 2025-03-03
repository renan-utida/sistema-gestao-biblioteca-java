package org.example;

import java.util.Scanner;

public class GerenciarEmprestimos {
    private Biblioteca biblioteca;
    private Scanner scanner;

    public GerenciarEmprestimos(Biblioteca biblioteca, Scanner scanner) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
    }

    public void registrarEmprestimo() {
        if (biblioteca.exibirMensagemSeListaVazia(biblioteca.getLivros(), "Nenhum livro registrado no sistema.") ||
                biblioteca.exibirMensagemSeListaVazia(biblioteca.getMembros(), "Nenhum membro registrado no sistema.")) {
            return;
        }

        System.out.println("\nLivros cadastrados:");
        biblioteca.listarTodosLivros();

        System.out.println("\nMembros cadastrados:");
        biblioteca.listarTodosMembros();

        int isbn = LeituraDeDados.lerInteiro("\nISBN do Livro: ");
        int id = LeituraDeDados.lerInteiro("ID do Membro: ");

        biblioteca.registrarNovoEmprestimo(isbn, id);
    }

    public void devolverLivro() {
        System.out.println("\nEmpréstimos ativos:");
        biblioteca.listarTodosEmprestimos();

        if (biblioteca.exibirMensagemSeListaVazia(biblioteca.getEmprestimos(), "\nNenhum empréstimo registrado no sistema.")) {
            return;
        }

        int isbn = LeituraDeDados.lerInteiro("\nISBN do Livro: ");
        int id = LeituraDeDados.lerInteiro("ID do Membro: ");

        biblioteca.devolverLivroBiblioteca(isbn, id);
    }

    public void listarEmprestimos() {
        biblioteca.listarTodosEmprestimos();
    }
}