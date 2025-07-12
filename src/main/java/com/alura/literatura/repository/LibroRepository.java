package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreTitulo);
    @Query(value = "SELECT * FROM libros ORDER BY totalDescargas DESC LIMIT 10", nativeQuery = true)
    List<Libro> top10LibrosMasDescargados();


    @Query("SELECT E FROM Libros E JOIN E.idiomas e WHERE e = :idioma")
    List<Libro> findByContainingIdioma(@Param("idioma") String idioma);

}
