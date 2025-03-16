package org.example;

import java.util.*;

public class GerenciarLivros {
    private Biblioteca biblioteca;
    private Scanner scanner;

    public GerenciarLivros(Biblioteca biblioteca, Scanner scanner) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
    }

    public void adicionarLivro() {
        biblioteca.exibirISBNsCadastrados();

        System.out.print("\nTítulo: ");
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

        int isbn = LeituraDeDados.lerInteiro("ISBN: ");
        if (isbn == -1) {
            return;
        }

        if (biblioteca.existeLivro(isbn)) {
            System.out.println("Erro: Já existe um livro com esse ISBN.");
            return;
        }

        Livro livro = new Livro(titulo, autor, isbn);
        biblioteca.adicionarNovoLivro(livro);
    }

    public void removerLivro() {
        if (biblioteca.getLivros().isEmpty()) {
            System.out.println("\nNenhum livro registrado no sistema.");
            return;
        }

        System.out.println("\nLivros cadastrados:");
        biblioteca.listarTodosLivros();

        int isbn = LeituraDeDados.lerInteiro("\nISBN do livro para remover: ");
        if (isbn == -1) {
            return;
        }
        biblioteca.removerLivroBiblioteca(isbn);
    }

    public void editarLivro() {
        if (biblioteca.getLivros().isEmpty()) {
            System.out.println("\nNenhum livro registrado no sistema.");
            return;
        }

        biblioteca.listarTodosLivros();

        int isbn = LeituraDeDados.lerInteiro("\nISBN do livro para editar: ");
        if (isbn == -1) {
            return;
        }

        Livro livro = biblioteca.getLivros().stream()
                .filter(l -> l.getIsbn() == isbn)
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Erro: Livro com ISBN " + isbn + " não encontrado.");
            return;
        }

        System.out.print("Novo Título: ");
        String novoTitulo = scanner.nextLine();
        if (novoTitulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }

        System.out.print("Novo Autor: ");
        String novoAutor = scanner.nextLine();
        if (novoAutor.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor não pode ser vazio.");
        } else if (!novoAutor.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Autor deve conter apenas letras e espaços.");
        }

        biblioteca.editarLivro(isbn, novoTitulo, novoAutor);
    }

    public void listarLivros() {
        biblioteca.listarTodosLivros();
    }
}