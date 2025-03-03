# Sistema de Gestão de Biblioteca em Java

Este é um sistema de gestão de biblioteca desenvolvido em Java como parte de um exercício prático. O sistema permite gerenciar livros, membros e empréstimos de uma biblioteca, seguindo boas práticas de programação e utilizando conceitos como classes, atributos, métodos, listas, tratamento de exceções e manipulação de arquivos.

## Tecnologia Utilizada
- Java

<img src="https://github.com/user-attachments/assets/fc2e0fc7-e5d9-40a9-8681-9f0c09c23705" alt="java-logo" width="40" height="auto"/>

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. **Gerenciamento de Livros:**
- Adicionar um novo livro.
- Remover um livro existente.
- Listar todos os livros cadastrados.

2. **Gerenciamento de Membros:**
- Registrar um novo membro.
- Listar todos os membros cadastrados.

3. **Gerenciamento de Empréstimos:**
- Registrar um empréstimo de livro para um membro.
- Devolver um livro emprestado.
- Listar todos os empréstimos ativos.

4. **Persistência de Dados:**
- Os dados são salvos em um arquivo `biblioteca.txt` e carregados automaticamente ao iniciar o sistema.

## Estrutura do Projeto

O projeto é organizado em várias classes, cada uma com uma responsabilidade específica:

### Classes Principais

1. **Principal**:
   - Classe principal que inicia o sistema e gerencia o menu de opções.
   - Centraliza o uso do `Scanner` para entrada de dados.
   - Fecha o `Scanner` ao encerrar o programa.

2. **Biblioteca**:
   - Classe central que gerencia as listas de livros, membros e empréstimos.
   - Implementa métodos para adicionar, remover e listar livros, membros e empréstimos.
   - Verifica se um livro ou membro já existe antes de adicionar.
   - Impede que um membro pegue o mesmo livro duas vezes.

3. **Livro**:
   - Representa um livro com atributos como título, autor e ISBN.
   - Implementa o método `toString()` para exibir detalhes do livro.

4. **Membro**:
   - Representa um membro da biblioteca com atributos como nome, ID e email.
   - Implementa o método `toString()` para exibir detalhes do membro.

5. **Emprestimo**:
   - Representa um empréstimo de livro, contendo o livro, o membro e a data do empréstimo.
   - Implementa o método `toString()` para exibir detalhes do empréstimo.

### Classes de Gerenciamento

6. **GerenciarLivros**:
   - Gerencia as operações relacionadas a livros (adicionar, remover, listar).

7. **GerenciarMembros**:
   - Gerencia as operações relacionadas a membros (registrar, listar).

8. **GerenciarEmprestimos**:
   - Gerencia as operações relacionadas a empréstimos (registrar, devolver, listar).

### Classes de Persistência

9. **CarregarDados**:
   - Carrega os dados de livros, membros e empréstimos de um arquivo `biblioteca.txt`.

10. **SalvarDados**:
    - Salva os dados de livros, membros e empréstimos em um arquivo `biblioteca.txt`.

### Utilitários

11. **LeituraDeDados**:
    - Classe utilitária para ler números inteiros da entrada do usuário, com tratamento de erros.

## Como Executar o Projeto

1. **Pré-requisitos:**
   - Java Development Kit (JDK) instalado.
   - Ambiente de desenvolvimento (IDE como IntelliJ, Eclipse ou VS Code) ou terminal.

2. **Compilação e Execução:**
  - Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-biblioteca-java.git
  ```
  - Navegue até o diretório do projeto:
   ```bash
   cd sistema-biblioteca-java
  ```
  - Compile o projeto:
  ```bash
   javac -d bin src/org/example/*.java
  ```
  - Execute o programa:
  ```bash
   java -cp bin org.example.Principal
  ```

3. **Uso do Sistema:**
   - Siga as instruções no menu para gerenciar livros, membros e empréstimos.
   - Os dados são salvos automaticamente no arquivo `biblioteca.txt`.

## Exemplo de Uso

1. **Adicionar um Livro:**
   - Escolha a opção "Gerenciar Livros" no menu principal.
   - Selecione "Adicionar Livro" e insira os detalhes do livro (título, autor, ISBN).

2. **Registrar um Membro:**
   - Escolha a opção "Gerenciar Membros" no menu principal.
   - Selecione "Registrar Membro" e insira os detalhes do membro (nome, ID, email).

3. **Registrar um Empréstimo:**
   - Escolha a opção "Empréstimos e Devoluções" no menu principal.
   - Selecione "Registrar Empréstimo" e insira o ISBN do livro e o ID do membro.

4. **Devolver um Livro:**
   - Escolha a opção "Empréstimos e Devoluções" no menu principal.
   - Selecione "Devolver Livro" e insira o ISBN do livro e o ID do membro.

## Desenvolvedor

[<img loading="lazy" src="https://github.com/user-attachments/assets/b4f96f4b-542e-4988-9bc1-b1acf22a41a1" width=115><br><sub>Renan Dias Utida</sub>](https://github.com/renan-utida)

### Linkedin: https://www.linkedin.com/in/renan-dias-utida-1b1228225/

## Considerações Finais
Este projeto foi uma excelente oportunidade para aplicar conceitos fundamentais de programação em Java, como orientação a objetos, manipulação de listas, tratamento de exceções e persistência de dados em arquivos. Além disso, permitiu a prática de boas práticas de desenvolvimento, como a organização do código em classes coesas e a centralização de responsabilidades.

### Aprendizados
- **Organização do Código:** A divisão do sistema em classes bem definidas (como Biblioteca, Livro, Membro e Emprestimo) facilitou a manutenção e a escalabilidade do projeto.
- **Tratamento de Exceções:** A implementação de try/catch e a validação de entradas do usuário ajudaram a criar um sistema mais robusto e amigável.
- **Persistência de Dados:** A utilização de arquivos (biblioteca.txt) para salvar e carregar dados mostrou como é possível manter informações entre execuções do programa.
- **Uso de Streams:** A aplicação de streams para filtrar e manipular listas trouxe eficiência e clareza ao código.

## Desafios Enfrentados
- **Validação de Entradas:** Garantir que o usuário insira dados válidos (como números inteiros para ISBN e ID) exigiu a criação de métodos específicos, como LeituraDeDados.lerInteiro.
- **Gerenciamento de Empréstimos:** Implementar a lógica para evitar que um membro pegue o mesmo livro duas vezes foi um desafio interessante, resolvido com a verificação de empréstimos ativos.
- **Fechamento do Scanner:** Aprender a gerenciar o Scanner de forma centralizada e fechá-lo corretamente ao final do programa foi um aprendizado valioso.

## Agradecimentos
Agradeço ao meu professor pelo apoio e feedback durante o desenvolvimento deste projeto.

Este projeto foi uma jornada de aprendizado e crescimento, e estou animado para continuar desenvolvendo minhas habilidades em Java e em desenvolvimento de software em geral.
