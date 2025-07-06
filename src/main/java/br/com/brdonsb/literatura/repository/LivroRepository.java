package br.com.brdonsb.literatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brdonsb.literatura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
