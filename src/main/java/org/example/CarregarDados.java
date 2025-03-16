package org.example;

import java.io.*;
import java.util.*;

public class CarregarDados {
    private static final String ARQUIVO = "biblioteca.txt";

    public void carregar(List<Livro> livros, List<Membro> membros, List<Emprestimo> emprestimos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Livro:")) {
                    String[] partes = linha.substring(6).split(", ");
                    String titulo = partes[0].split("=")[1];
                    String autor = partes[1].split("=")[1];
                    int isbn = Integer.parseInt(partes[2].split("=")[1].replace("}", ""));
                    livros.add(new Livro(titulo, autor, isbn));
                } else if (linha.startsWith("Membro:")) {
                    String[] partes = linha.substring(7).split(", ");
                    String nome = partes[0].split("=")[1];
                    int id = Integer.parseInt(partes[1].split("=")[1]);
                    String email = partes[2].split("=")[1].replace("}", "");
                    membros.add(new Membro(nome, id, email));
                } else if (linha.startsWith("Emprestimo:")) {
                    String[] partes = linha.substring(11).split(", ");
                    String tituloLivro = partes[0].split("=")[1];
                    String nomeMembro = partes[1].split("=")[1];
                    String data = partes[2].split("=")[1].replace("}", "");

                    Livro livro = livros.stream().filter(l -> l.getTitulo().equals(tituloLivro)).findFirst().orElse(null);
                    Membro membro = membros.stream().filter(m -> m.getNome().equals(nomeMembro)).findFirst().orElse(null);

                    if (livro != null && membro != null) {
                        emprestimos.add(new Emprestimo(livro, membro, new Date()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum dado anterior encontrado.");
        }
    }
}