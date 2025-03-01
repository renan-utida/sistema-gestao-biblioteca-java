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

        System.out.print("ISBN do Livro: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do Membro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        biblioteca.registroEmprestimo(isbn, id);
    }

    public void devolverLivro() {
        Scanner scanner = new Scanner(System.in);

        if (biblioteca.exibirMensagemSeListaVazia(biblioteca.getEmprestimos(), "Nenhum empréstimo registrado no sistema.")) {
            return;
        }

        System.out.print("ISBN do Livro: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do Membro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        biblioteca.devolucaoLivro(isbn, id);
    }

    public void listarEmprestimos() {
        biblioteca.listagemEmprestimos();
    }
}