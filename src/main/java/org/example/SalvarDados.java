package org.example;

import java.io.*;
import java.util.*;

public class SalvarDados {
    private static final String ARQUIVO = "biblioteca.txt";

    public void salvar(List<Livro> livros, List<Membro> membros, List<Emprestimo> emprestimos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            writer.write("---------------- Livros ----------------");
            writer.newLine();
            for (Livro livro : livros) {
                writer.write("Livro: {titulo=" + livro.getTitulo() + ", autor=" + livro.getAutor() + ", ISBN=" + livro.getIsbn() + "}");
                writer.newLine();
            }
            writer.newLine();
            writer.write("---------------- Membros ----------------");
            writer.newLine();
            for (Membro membro : membros) {
                writer.write("Membro: {nome=" + membro.getNome() + ", id=" + membro.getId() + ", email=" + membro.getEmail() + "}");
                writer.newLine();
            }
            writer.newLine();
            writer.write("---------------- Empréstimos ----------------");
            writer.newLine();
            for (Emprestimo emprestimo : emprestimos) {
                writer.write("Emprestimo: {livro=" + emprestimo.getLivro().getTitulo() + ", membro=" + emprestimo.getMembro().getNome() + ", data=" + emprestimo.getDataEmprestimo() + "}");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
