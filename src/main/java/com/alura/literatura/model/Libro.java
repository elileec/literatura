package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Libro {

    private String titulo;
    private List<RegistroAutor> autor;
    private List<String> idiomas;
    private int fechaNacimiento;
    private int fechaFallecimiento;
    private Integer totalDescargas;


    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor();
        this.idiomas = datosLibro.idiomas();
        this.fechaNacimiento = datosLibro.fechaNacimiento();
        this.fechaFallecimiento = datosLibro.fechaFallecimiento();
        this.totalDescargas = datosLibro.totalDescargas();


    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<RegistroAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<RegistroAutor> autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(int fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento +
                ", totalDescargas=" + totalDescargas;
    }
}
