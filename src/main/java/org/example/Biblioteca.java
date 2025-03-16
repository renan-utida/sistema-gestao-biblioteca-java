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

    public void editarLivro(int isbn, String novoTitulo, String novoAutor) {
        Livro livro = livros.stream().filter(l -> l.getIsbn() == isbn).findFirst().orElse(null);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            salvarDados.salvar(livros, membros, emprestimos);
            System.out.println("Livro editado com sucesso!");
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

    public void removerMembro(int id) {
        if (membros.isEmpty()) {
            System.out.println("Nenhum membro cadastrado no sistema.");
            return;
        }

        boolean removido = membros.removeIf(m -> m.getId() == id);
        if (removido) {
            salvarDados.salvar(livros, membros, emprestimos);
            System.out.println("Membro removido com sucesso!");
        } else {
            System.out.println("Erro: Membro com ID " + id + " não encontrado.");
        }
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
            boolean emprestimoAtivo = emprestimos.stream()
                    .anyMatch(e -> e.getLivro().getIsbn() == isbn && e.getMembro().getId() == membroId);

            if (emprestimoAtivo) {
                System.out.println("Erro: O membro já possui um empréstimo ativo para este livro.");
                return;
            }

            emprestimos.add(new Emprestimo(livro, membro, new Date()));
            salvarDados.salvar(livros, membros, emprestimos);
            System.out.println("Empréstimo registrado com sucesso!");
        } else {
            System.out.println("Erro: Livro ou membro não encontrado.");
        }
    }

    public void devolverLivroBiblioteca(int numeroEmprestimo) {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado no sistema.");
            return;
        }

        if (numeroEmprestimo < 1 || numeroEmprestimo > emprestimos.size()) {
            System.out.println("Erro: Número de empréstimo inválido.");
            return;
        }

        Emprestimo emprestimo = emprestimos.get(numeroEmprestimo - 1);
        emprestimos.remove(emprestimo);
        salvarDados.salvar(livros, membros, emprestimos);
        System.out.println("Livro devolvido com sucesso!");
    }


    public void listarTodosLivros() {
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
            return;
        }

        List<Livro> livrosOrdenados = livros.stream()
                .sorted(Comparator.comparingInt(Livro::getIsbn))
                .toList();

        System.out.println("\nLivros cadastrados:");
        for (Livro livro : livrosOrdenados) {
            System.out.println("-> Livro: " + livro.getTitulo() + ", " + livro.getAutor() + " - ISBN = " + livro.getIsbn());
        }
    }

    public void listarTodosMembros() {
        if (membros.isEmpty()) {
            System.out.println("\nNenhum membro cadastrado.");
            return;
        }

        List<Membro> membrosOrdenados = membros.stream()
                .sorted(Comparator.comparingInt(Membro::getId))
                .toList();

        System.out.println("\nMembros cadastrados:");
        for (Membro membro : membrosOrdenados) {
            System.out.println("-> ID = " + membro.getId());
            System.out.println("   - Nome = " + membro.getNome());
            System.out.println("   - Email = " + membro.getEmail());
        }
    }

    public void listarTodosEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("\nNenhum empréstimo registrado.");
            return;
        }

        System.out.println("\nEmpréstimos ativos:");
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emprestimo = emprestimos.get(i);
            System.out.println("-> Empréstimo " + (i + 1));
            System.out.println("   - Livro: " + emprestimo.getLivro().getTitulo() + ", " + emprestimo.getLivro().getAutor() + " - ISBN = " + emprestimo.getLivro().getIsbn());
            System.out.println("   - Membro: " + emprestimo.getMembro().getNome() + ", " + emprestimo.getMembro().getEmail() + " - ID = " + emprestimo.getMembro().getId());
            System.out.println("   - Data de Empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println();
        }
    }

    public void exibirISBNsCadastrados() {
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
            return;
        }

        List<Integer> isbns = livros.stream()
                .map(Livro::getIsbn)
                .sorted()
                .toList();

        System.out.println("\nISBNs já cadastrados: ");
        System.out.print("-> ");
        for (int i = 0; i < isbns.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(isbns.get(i));
        }
        System.out.println();
    }

    public void exibirIDsCadastrados() {
        if (membros.isEmpty()) {
            System.out.println("\nNenhum membro cadastrado.");
            return;
        }

        List<Integer> ids = membros.stream()
                .map(Membro::getId)
                .sorted()
                .toList();

        System.out.println("\nIDs já cadastrados:");
        System.out.print("-> ");
        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(ids.get(i));
        }
        System.out.println();
    }

    public <T> boolean exibirMensagemSeListaVazia(List<T> lista, String mensagem) {
        if (lista.isEmpty()) {
            System.out.println(mensagem);
            return true;
        }
        return false;
    }
}