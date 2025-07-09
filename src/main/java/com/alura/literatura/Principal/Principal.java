package com.alura.literatura.Principal;

//import com.alura.literatura.model.Libro;
import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.LibroBusqueda;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibro> datoslibros = new ArrayList<>();

    

    public void Menu(){
        var opcion=-1;

        while(opcion!=0){
            var menu= """
                    1. Buscar libro
                    2. Mostrar libros buscados
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

    https://github.com/pedro8734/literatura/blob/main/src/main/java/com/aluracursos/desafioLibros/principal/Principal.java
    comparar el codigo de cada clase
    vamos en el video https://app.aluracursos.com/course/java-persistencia-datos-consultas-spring-data-jpa/task/87369


    private DatosLibro getLibros(){
        System.out.println("Escriba el nombre del libro a buscar");
        String libro = teclado.nextLine();
        String json = consumoApi.obtenerDatos("https://gutendex.com/books"+"/?search=" + libro.replace(" ","+"));
        System.out.println(json);
        ConvierteDatos conversor = new ConvierteDatos();
        var datos = conversor.obtenerDatos(json, LibroBusqueda.class);
        //System.out.println("ESTOS DON LOS DATOS DESPUES DE CONVERTIR"+datos.resultados().getFirst().autor().getFirst().nombre());

        return (datos.resultados().get(0));


    }
    private void EncontrarLibro() {
         DatosLibro datos = getLibros();
         datoslibros.add(datos);
        System.out.println(datos);



      //  https://app.aluracursos.com/course/java-persistencia-datos-consultas-spring-data-jpa/task/87368
      //    System.out.println("ESTE FUE EL LIBRO QUE ENCONTRE" + respuesta.resultados().get(0).autor().get(0).fechaFallecimiento());

    }

    private void mostrarLibrosBuscados() {
        datoslibros.forEach(System.out::println);
    }

}
