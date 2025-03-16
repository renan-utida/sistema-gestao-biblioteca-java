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

        biblioteca.listarTodosLivros();
        biblioteca.exibirISBNsCadastrados();
        int isbn = LeituraDeDados.lerInteiro("\nISBN do Livro: ");
        if (isbn == -1 || !biblioteca.existeLivro(isbn)) {
            System.out.println("Erro: ISBN inválido ou livro não encontrado.");
            return;
        }

        biblioteca.listarTodosMembros();
        biblioteca.exibirIDsCadastrados();
        int id = LeituraDeDados.lerInteiro("\nID do Membro: ");
        if (id == -1 || !biblioteca.existeMembro(id)) {
            System.out.println("Erro: ID inválido ou membro não encontrado.");
            return;
        }

        biblioteca.registrarNovoEmprestimo(isbn, id);
    }

    public void devolverLivro() {
        if (biblioteca.getEmprestimos().isEmpty()) {
            System.out.println("\nNenhum empréstimo registrado no sistema.");
            return;
        }

        System.out.println("\nEmpréstimos ativos:");
        biblioteca.listarTodosEmprestimos();

        int numeroEmprestimo = LeituraDeDados.lerInteiro("\nNúmero do empréstimo para devolver: ");
        if (numeroEmprestimo == -1) {
            return;
        }

        biblioteca.devolverLivroBiblioteca(numeroEmprestimo);
    }

    public void listarEmprestimos() {
        biblioteca.listarTodosEmprestimos();
    }
}