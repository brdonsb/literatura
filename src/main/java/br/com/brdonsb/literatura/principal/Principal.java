package br.com.brdonsb.literatura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;
        while (opcao!= 0) {
            var menu = """
                    ********** Literatura **********

                    Escolha digite a opção desejada

                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Lista autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo ...");
                    break;
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarAutoresRegistrados();
                break;
                case 3:
                    listarAutoresRegistrados();
                break;
                case 4:
                    listarAutoresVivosNoAno();
                break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    private void buscarLivroPorTitulo() {
    }
    private void listarAutoresRegistrados() {
    }
    private void listarAutoresVivosNoAno() {
    }
    private void listarLivrosPorIdioma() {
    }
}
