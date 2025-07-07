package br.com.brdonsb.literatura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Column(unique = true)
    private String nome;
    private int anoNascimento;
    private int anoMorte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoMorte = dadosAutor.anoMorte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(int anoMorte) {
        this.anoMorte = anoMorte;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        String titulos = livros.stream()
                           .map(Livro::getTitulo)
                           .collect(Collectors.joining(" - ", "[", "]"));

        return
                "\nNome: " + nome +
                "\nAno de Nascimento: " + anoNascimento +
                "\nAno de Morte: " + anoMorte +
                "\nLivros: " + titulos;
    }
}