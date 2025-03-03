package org.example;

import java.util.Scanner;

public class GerenciarMembros {
    private Biblioteca biblioteca;
    private Scanner scanner;

    public GerenciarMembros(Biblioteca biblioteca, Scanner scanner) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
    }

    public void registrarMembro() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        } else if (!nome.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras e espaços.");
        }

        int id = LeituraDeDados.lerInteiro("ID: ");

        if (biblioteca.existeMembro(id)) {
            System.out.println("Erro: Já existe um membro com esse ID.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio.");
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        Membro membro = new Membro(nome, id, email);
        biblioteca.registrarNovoMembro(membro);
    }

    public void listarMembros() {
        biblioteca.listarTodosMembros();
    }
}
