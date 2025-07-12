package com.alura.literatura;

import com.alura.literatura.Principal.Principal;
//import com.alura.literatura.model.DatosLibro;
//import com.alura.literatura.model.Libro;
//import com.alura.literatura.model.LibroBusqueda;
//import com.alura.literatura.service.ConsumoAPI;
//import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repolibro;
	@Autowired
	private AutorRepository repoautor;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repolibro, repoautor);
		principal.Menu();


	}

}

