package org.example;

import java.util.*;

public class GerenciarLivros {
    private Biblioteca biblioteca;

    public GerenciarLivros(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void adicionarLivro() {
        Scanner scanner = new Scanner(System.in);
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
        int isbn = scanner.nextInt();
        scanner.nextLine();

        if (biblioteca.existeLivro(isbn)) {
            System.out.println("Erro: Já existe um livro com esse ISBN.");
            return;
        }

        Livro livro = new Livro(titulo, autor, isbn);
        biblioteca.novoLivro(livro);
    }

    public void removerLivro() {
        Scanner scanner = new Scanner(System.in);
        if (biblioteca.exibirMensagemSeListaVazia(biblioteca.getLivros(), "Nenhum livro registrado no sistema.")) {
            return;
        }
        System.out.print("ISBN do livro para remover: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        biblioteca.remocaoLivro(isbn);
    }

    public void listarLivros() {
        biblioteca.listagemLivros();
    }
}