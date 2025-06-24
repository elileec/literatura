package com.alura.literatura;

import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.LibroBusqueda;
import com.alura.literatura.model.RegistroAutor;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		String titulo = "Bleak House";
		String json = consumoApi.obtenerDatos("https://gutendex.com/books"+"/?search=" + titulo.replace(" ","+"));
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		LibroBusqueda datos = conversor.obtenerDatos(json, LibroBusqueda.class);
		System.out.println(datos.resultados().get(0).titulo()+" "+datos.resultados().get(0).totalDescargas());

	}
}
// seguir estos pasos
//https://github.com/pedro8734/literatura/blob/main/src/main/java/com/aluracursos/desafioLibros/model/Datos.java
