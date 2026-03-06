package com.aluracursos.literAlura.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, columnDefinition = "TEXT")
    private String nombre;
    private Integer ano_nacimiento;
    private Integer ano_muerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.ano_nacimiento = datosAutor.anoNacimiento();
        this.ano_muerte = datosAutor.anoMerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAno_nacimiento() {
        return ano_nacimiento;
    }

    public void setAno_nacimiento(Integer ano_nacimiento) {
        this.ano_nacimiento = ano_nacimiento;
    }

    public Integer getAno_muerte() {
        return ano_muerte;
    }

    public void setAno_muerte(Integer ano_muerte) {
        this.ano_muerte = ano_muerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor: " + nombre +
                "Murio en: " + ano_muerte +
                "Nacio en:" + ano_nacimiento;
    }
}
