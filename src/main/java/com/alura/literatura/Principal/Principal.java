package com.alura.literatura.Principal;

import com.alura.literatura.model.*;
import com.alura.literatura.model.Libro;
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
    private LibroRepository repolibro;
    private AutorRepository repoAutor;
    private List<Libro> libros;
    private Optional<Libro> buscandoLibro;

    public Principal(LibroRepository repolibro, AutorRepository repoautor) {
        this.repolibro = repolibro;
        this.repoAutor = repoautor;
    }

    public void Menu(){
        var opcion=-1;

        while(opcion!=0){
            var menu= """
                    1. Buscar libro.
                    2. Mostrar libros almacenados.
                    3. Buscar libro por título.
                    4. Lista los libros registrados.
                    5. Lista los autores registrados.
                    6. Los diez libros con mas descargas.
                    7. Bucasr libro por idioma.
                    8. Mostrar autores vivos por año.
                    9. Los cinco autores con más tiempo de fallecidos.
                    10.Buscar autor por su nombre. 
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
         if(datos == null)return;
         Optional<Libro> libroAllado = repolibro.findByTituloContainsIgnoreCase(datos.titulo());
         if (libroAllado.isPresent()){
             System.out.println("Libro ya existe en la base de deatos");
         }else{
             Libro libro = new Libro(datos);
             repolibro.save(libro);
             if(datos.idiomas() !=null){
                 List<String> idiomas = datos.idiomas().stream()
                         .map(String::toLowerCase)
                         .distinct()
                         .collect(Collectors.toList());
                 libro.setIdiomas(idiomas);
             }
             if(datos.autor()!=null){
                 List<Autor> autores = (List<Autor>) datos.autor().stream()
                         .map(datoAutor -> repoAutor
                         .findByNombre(datoAutor.nombre())
                         .orElseGet(()-> repoAutor.save(new Autor(datoAutor))))
                         .collect(Collectors.toList());
                 libro.setAutores(autores);
                 autores.forEach(i->i.setLibros(List.of(libro)));
             }
             System.out.println("Libro fue almacenado");
             System.out.println(libro);
         }

    }

    private void mostrarLibrosBuscados() {
        datoslibros.forEach(System.out::println);
    }

}
