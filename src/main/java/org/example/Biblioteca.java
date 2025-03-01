package org.example;

import java.io.*;
import java.util.*;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Membro> membros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private static final String ARQUIVO = "biblioteca.txt";

    public List<Livro> getLivros() {
        return livros;
    }
    public List<Membro> getMembros() {
        return membros;
    }
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public Biblioteca() {
        carregarDados(); // Carregar dados ao iniciar
    }

    public void adicionarLivro(Livro livro) {
        for (Livro l : livros) {
            if (l.getIsbn() == livro.getIsbn()) {
                System.out.println("Erro: Já existe um livro com esse ISBN.");
                return;
            }
        }
        livros.add(livro);
        salvarDados();
        System.out.println("Livro adicionado com sucesso!");
    }

    public void removerLivro(int isbn) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no sistema.");
            return;
        }

        boolean removido = livros.removeIf(l -> l.getIsbn() == isbn);
        if (removido) {
            salvarDados();
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Erro: Livro com ISBN " + isbn + " não encontrado.");
        }
    }

    public void registrarMembro(Membro membro) {
        for (Membro m : membros) {
            if (m.getId() == membro.getId()) {
                System.out.println("Erro: Já existe um membro com esse ID.");
                return;
            }
        }
        membros.add(membro);
        salvarDados();
        System.out.println("Membro registrado com sucesso!");
    }

    public boolean existeLivro(int isbn) {
        return livros.stream().anyMatch(m -> m.getIsbn() == isbn);
    }

    public boolean existeMembro(int id) {
        return membros.stream().anyMatch(m -> m.getId() == id);
    }

    public void registrarEmprestimo(int isbn, int membroId) {
        Livro livro = livros.stream().filter(l -> l.getIsbn() == isbn).findFirst().orElse(null);
        Membro membro = membros.stream().filter(m -> m.getId() == membroId).findFirst().orElse(null);

        if (livro != null && membro != null) {
            emprestimos.add(new Emprestimo(livro, membro, new Date()));
            salvarDados();
            System.out.println("Empréstimo registrado com sucesso!");
        } else {
            System.out.println("Erro: Livro ou membro não encontrado.");
        }
    }

    public void devolverLivro(int isbn, int membroId) {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado no sistema.");
            return;
        }

        Optional<Emprestimo> emprestimo = emprestimos.stream()
                .filter(e -> e.getLivro().getIsbn() == isbn && e.getMembro().getId() == membroId)
                .findFirst();

        if (emprestimo.isPresent()) {
            emprestimos.remove(emprestimo.get());
            salvarDados();
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro: Nenhum empréstimo encontrado para este livro e usuário.");
        }
    }

    public void listarLivros() {
        if (exibirMensagemSeListaVazia(livros, "Nenhum livro cadastrado.")) return;
        livros.forEach(System.out::println);
    }

    public void listarMembros() {
        if (exibirMensagemSeListaVazia(membros, "Nenhum membro cadastrado.")) return;
        membros.forEach(System.out::println);
    }

    public void listarEmprestimos() {
        if (exibirMensagemSeListaVazia(emprestimos, "Nenhum empréstimo registrado.")) return;
        emprestimos.forEach(System.out::println);
    }

    public <T> boolean exibirMensagemSeListaVazia(List<T> lista, String mensagem) {
        if (lista.isEmpty()) {
            System.out.println(mensagem);
            return true;
        }
        return false;
    }

    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Livro livro : livros) {
                writer.write("Livro: {titulo=" + livro.getTitulo() + ", autor=" + livro.getAutor() + ", ISBN=" + livro.getIsbn() + "}");
                writer.newLine();
            }
            for (Membro membro : membros) {
                writer.write("Membro: {nome=" + membro.getNome() + ", id=" + membro.getId() + ", email=" + membro.getEmail() + "}");
                writer.newLine();
            }
            for (Emprestimo emprestimo : emprestimos) {
                writer.write("Emprestimo: {livro=" + emprestimo.getLivro().getTitulo() + ", membro=" + emprestimo.getMembro().getNome() + ", data=" + emprestimo.getDataEmprestimo() + "}");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
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

                    // Encontrar o Livro e o Membro correspondentes
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