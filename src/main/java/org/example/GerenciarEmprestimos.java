package org.example;

import java.util.Scanner;

public class GerenciarEmprestimos {
    private Biblioteca biblioteca;

    public GerenciarEmprestimos(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void registrarEmprestimo() {
        Scanner scanner = new Scanner(System.in);

        if (biblioteca.exibirMensagemSeListaVazia(biblioteca.getLivros(), "Nenhum livro registrado no sistema.") ||
                biblioteca.exibirMensagemSeListaVazia(biblioteca.getMembros(), "Nenhum membro registrado no sistema.")) {
            return;
        }

        int isbn = LeituraDeDados.lerInteiro("ISBN do Livro: ");
        int id = LeituraDeDados.lerInteiro("ID do Membro: ");

        biblioteca.registrarNovoEmprestimo(isbn, id);
    }

    public void devolverLivro() {
        Scanner scanner = new Scanner(System.in);

        if (biblioteca.exibirMensagemSeListaVazia(biblioteca.getEmprestimos(), "Nenhum empréstimo registrado no sistema.")) {
            return;
        }

        int isbn = LeituraDeDados.lerInteiro("ISBN do Livro: ");
        int id = LeituraDeDados.lerInteiro("ID do Membro: ");

        biblioteca.devolverLivroBiblioteca(isbn, id);
    }

    public void listarEmprestimos() {
        biblioteca.listarTodosEmprestimos();
    }
}