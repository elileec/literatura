package com.alura.literatura.Principal;

import com.alura.literatura.model.Libro;
import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.LibroBusqueda;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private List<DatosLibro> datoslibros = new ArrayList<>();
    private LibroRepository libroRepo;
    private AutorRepository autorRepo;
    private List<Libro> libros;
    private Optional<Libro> buscandoLibro;

    public Principal(LibroRepository repolibro, AutorRepository repoautor) {
        this.libroRepo = repolibro;
        this.autorRepo = repoautor;
    }

    public void Menu(){
        var opcion=-1;

        while(opcion!=0){
            var menu= """
                    1. Buscar libro
                    2. Mostrar libros almacenados
                    0. Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    EncontrarLibro();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 0:
                    System.out.println("ESTOY SALIENDO DEL PROGRAMA");
                    break;
                default:
                    System.out.println("OPCION INVALIDA");
            }
        }
    }


    private DatosLibro getLibros(){
        System.out.println("Escriba el nombre del libro a buscar");
        String libro = teclado.nextLine();
        String json = consumoApi.obtenerDatos("https://gutendex.com/books"+"/?search=" + libro.replace(" ","+"));
        System.out.println(json);
        ConvierteDatos conversor = new ConvierteDatos();
        var datos = conversor.obtenerDatos(json, LibroBusqueda.class);
        return (datos.resultados().get(0));
    }

    private void EncontrarLibro() {
         DatosLibro datos = getLibros();
         datoslibros.add(datos);
    }

    private void mostrarLibrosBuscados() {
        datoslibros.forEach(System.out::println);
    }

}
