# 📚 LiterAlura - Catálogo de Libros

Aplicación de consola desarrollada en Java con Spring Boot que permite buscar libros a través de la API de **Gutendex** (Project Gutenberg) y gestionar un catálogo local persistido en PostgreSQL.

## 📖 Descripción

LiterAlura es un **catálogo de libros interactivo** que consume la API pública [Gutendex](https://gutendex.com), parsea las respuestas JSON con Jackson y persiste los datos de libros y autores en una base de datos PostgreSQL mediante Spring Data JPA.

El usuario interactúa con la aplicación a través de un menú en consola donde puede buscar, listar y filtrar libros y autores.

---

## Funcionalidades

| # | Funcionalidad |
|---|--------------|
| 1 | Buscar libro por título (consulta a la API y guarda en BD) |
| 2 | Listar todos los libros registrados |
| 3 | Listar todos los autores registrados |
| 4 | Listar autores vivos en un determinado año |
| 5 | Listar libros por idioma |

---

## Tecnologías

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA** — persistencia y consultas con derived queries
- **PostgreSQL** — base de datos relacional
- **Jackson 2.16** — deserialización de JSON
- **Gutendex API** — fuente de datos de libros ([gutendex.com](https://gutendex.com))
- **HttpClient** — cliente HTTP nativo de Java 11+

---


## Autor Arturo Morales Pedroza

Desarrollado como parte del challenge **Oracle Next Education (ONE)** — Alura Latam.
