package br.com.brdonsb.literatura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.brdonsb.literatura.model.Livro;
import jakarta.transaction.Transactional;
import br.com.brdonsb.literatura.model.Autor;


public interface LivroRepository extends JpaRepository<Livro, Long>{

    @Query("SELECT a FROM Autor a WHERE a.nome = :nomeAutor")
    Autor buscarAutorPorNome(String nomeAutor);

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo AND l.autor.id = :id")
    Livro buscarLivroPorNomeEAutor(String titulo, Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO livros (idioma, numero_downloads, titulo, autor_id) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void inserirLivro(String idioma, Long numeroDownloads, String titulo, Long autorId);

    @Query("SELECT a FROM Autor a")
    List<Autor> buscarAutorCadastrado();

}
