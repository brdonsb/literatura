package br.com.brdonsb.literatura;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.brdonsb.literatura.principal.Principal;
import br.com.brdonsb.literatura.repository.LivroRepository;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner{
	@Autowired
	private LivroRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();	
	} 

}
