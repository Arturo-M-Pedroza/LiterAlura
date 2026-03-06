package com.aluracursos.literAlura.repository;

import com.aluracursos.literAlura.model.Lenguaje;
import com.aluracursos.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainsIgnoreCase(String titulo);

    Optional<Libro> findByTituloEqualsIgnoreCase(String titulo);

    @Query("SELECT b FROM Libro b WHERE b.lenguaje = :lenguajeEnum")
    List<Libro> librosRegistradoPorLenguaje(Lenguaje lenguajeEnum);
}
