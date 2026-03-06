package com.aluracursos.literAlura.repository;

import com.aluracursos.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombreAutor);

    @Query("SELECT a FROM Autor a WHERE a.ano_nacimiento <= :anio")
    List<Autor> autoresVivos(Integer anio);
}
