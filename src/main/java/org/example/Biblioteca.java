package org.example;

import java.util.*;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Membro> membros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private CarregarDados carregarDados = new CarregarDados();
    private SalvarDados salvarDados = new SalvarDados();

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
        carregarDados.carregar(livros, membros, emprestimos);
    }

    public void adicionarNovoLivro(Livro livro) {
        for (Livro l : livros) {
            if (l.getIsbn() == livro.getIsbn()) {
                System.out.println("Erro: Já existe um livro com esse ISBN.");
                return;
            }
        }
        livros.add(livro);
        salvarDados.salvar(livros, membros, emprestimos);
        System.out.println("Livro adicionado com sucesso!");
    }

    public void removerLivroBiblioteca(int isbn) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no sistema.");
            return;
        }

        boolean removido = livros.removeIf(l -> l.getIsbn() == isbn);
        if (removido) {
            salvarDados.salvar(livros, membros, emprestimos);
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Erro: Livro com ISBN " + isbn + " não encontrado.");
        }
    }


    public void registrarNovoMembro(Membro membro) {
        for (Membro m : membros) {
            if (m.getId() == membro.getId()) {
                System.out.println("Erro: Já existe um membro com esse ID.");
                return;
            }
        }
        membros.add(membro);
        salvarDados.salvar(livros, membros, emprestimos);
        System.out.println("Membro registrado com sucesso!");
    }

    public boolean existeLivro(int isbn) {
        return livros.stream().anyMatch(m -> m.getIsbn() == isbn);
    }

    public boolean existeMembro(int id) {
        return membros.stream().anyMatch(m -> m.getId() == id);
    }

    public void registrarNovoEmprestimo(int isbn, int membroId) {
        Livro livro = livros.stream().filter(l -> l.getIsbn() == isbn).findFirst().orElse(null);
        Membro membro = membros.stream().filter(m -> m.getId() == membroId).findFirst().orElse(null);

        if (livro != null && membro != null) {
            emprestimos.add(new Emprestimo(livro, membro, new Date()));
            salvarDados.salvar(livros, membros, emprestimos);
            System.out.println("Empréstimo registrado com sucesso!");
        } else {
            System.out.println("Erro: Livro ou membro não encontrado.");
        }
    }

    public void devolverLivroBiblioteca(int isbn, int membroId) {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado no sistema.");
            return;
        }

        Optional<Emprestimo> emprestimo = emprestimos.stream()
                .filter(e -> e.getLivro().getIsbn() == isbn && e.getMembro().getId() == membroId)
                .findFirst();

        if (emprestimo.isPresent()) {
            emprestimos.remove(emprestimo.get());
            salvarDados.salvar(livros, membros, emprestimos);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro: Nenhum empréstimo encontrado para este livro e usuário.");
        }
    }


    public void listarTodosLivros() {
        if (exibirMensagemSeListaVazia(livros, "Nenhum livro cadastrado.")) return;
        livros.forEach(System.out::println);
    }

    public void listarTodosMembros() {
        if (exibirMensagemSeListaVazia(membros, "Nenhum membro cadastrado.")) return;
        membros.forEach(System.out::println);
    }

    public void listarTodosEmprestimos() {
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
}