package com.alura.literatura;

import com.alura.literatura.Principal.Principal;
import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.LibroBusqueda;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	//	String titulo = "Frankenstein";
		Principal principal = new Principal();
		principal.Menu();


	}

}

