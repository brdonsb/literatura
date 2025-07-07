package br.com.brdonsb.literatura.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Long numeroDownloads;
    @ManyToOne (cascade = CascadeType.ALL)
    private Autor autor;

    public Livro(){}

    public Livro(String titulo, String idioma, Long numeroDownloads, Autor autor){
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public Long getNumeroDownloads() {
        return numeroDownloads;
    }
    public void setNumeroDownloads(Long numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    @Override
    public String toString() {
        return
            "********** LIVRO **********" +
            "\nTítulo: " + getTitulo() +
            "\nAutor: " + getAutor().getNome() +
            "\nIdioma: " + getIdioma() +
            "\nNúmero de downloads: " + getNumeroDownloads() +
            "\n***************************\n";
    }
}