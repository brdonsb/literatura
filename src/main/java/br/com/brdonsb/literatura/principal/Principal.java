package br.com.brdonsb.literatura.principal;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brdonsb.literatura.model.Autor;
import br.com.brdonsb.literatura.model.DadosAutor;
import br.com.brdonsb.literatura.model.Livro;
import br.com.brdonsb.literatura.repository.LivroRepository;
import br.com.brdonsb.literatura.service.ConsumoAPI;
import br.com.brdonsb.literatura.service.ConverteDados;
public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private Scanner leitura = new Scanner(System.in);
    private ObjectMapper mapper = new ObjectMapper();
    private LivroRepository repositorio;

    public Principal() {}

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

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
                    listarLivrosRegistrados();
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
        Livro livro = buscarLivroNaAPI();
        if (livro == null) {
            System.out.println("livro nao encontrado");            
        }else{
            System.out.printf("""
                ********** LIVRO **********
                Título = %s
                Autor = %s
                Idioma = %s
                Número de downloads = %d
                %n    """, livro.getTitulo(), livro.getAutor().getNome(), livro.getIdioma(), livro.getNumeroDownloads());            
            guardarDadosDB(livro);
        }
    }
    private void guardarDadosDB(Livro livro){
        Autor buscaAutorCadastrado = repositorio.buscarAutorPorNome(livro.getAutor().getNome());
        if (buscaAutorCadastrado == null) {
            repositorio.save(livro);
            return;           
        }
        Livro buscaLivroCadastrado = repositorio.buscarLivroPorNomeEAutor(livro.getTitulo(), buscaAutorCadastrado.getId());
        if (buscaLivroCadastrado == null) {
            repositorio.inserirLivro(livro.getIdioma(), livro.getNumeroDownloads(), livro.getTitulo(), buscaAutorCadastrado.getId());

        }else{
            //livro já cadastrado
        }
    }
    private Livro buscarLivroNaAPI(){
        System.out.println("Insira o nome do livro que você deseja procurar");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        try {
            JsonNode node = mapper.readTree(json);
            json = node.get("results").toString();
            json = json.substring(1, json.length() - 1);
            if (json == "") {
                return null;
            }
            node = mapper.readTree(json);
            String jsonTitulo = node.get("title").toString(); 
            jsonTitulo = jsonTitulo.substring(1, jsonTitulo.length() - 1);
            String jsonNumeroDownloads = node.get("download_count").toString();
            String jsonAutor = node.get("authors").toString();
            jsonAutor = jsonAutor.substring(1, jsonAutor.length() - 1);
            String jsonIdioma = node.get("languages").toString();
            jsonIdioma = jsonIdioma.substring(2, jsonIdioma.length() - 2);
            DadosAutor dados = conversor.obterDados(jsonAutor, DadosAutor.class);
            Autor autor = new Autor(dados);
            Livro livro = new Livro(jsonTitulo, jsonIdioma, Long.parseLong(jsonNumeroDownloads), autor);
            return livro;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void listarLivrosRegistrados(){
        List<Livro> livros = repositorio.findAll();
        livros.forEach(System.out::println);
    }
    private void listarAutoresRegistrados() {
        List<Autor> autores = repositorio.buscarAutorCadastrado();
        autores.forEach(System.out::println);
    }
    private void listarAutoresVivosNoAno() {
        System.out.println("Digite o ano");
        int ano = leitura.nextInt();
        List<Autor> autores = repositorio.buscarAutorVivoNoAno(ano);
        autores.forEach(System.out::println);        
    }
    private void listarLivrosPorIdioma() {
    }
}
