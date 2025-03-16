package org.example;

import java.util.Scanner;

public class LeituraDeDados {
    private static Scanner scanner = new Scanner(System.in);

    public static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine();

                if (valor <= 0) {
                    System.out.println("Erro: O valor deve ser maior que zero.");
                    return -1;
                }

                return valor;
            } catch (Exception e) {
                System.out.println("Erro: Digite um número válido.");
                scanner.nextLine();
                return -1;
            }
        }
    }
}
