package com.aluracursos.literAlura.principal;

import com.aluracursos.literAlura.model.*;
import com.aluracursos.literAlura.repository.IAutorRepository;
import com.aluracursos.literAlura.repository.ILibroRepository;
import com.aluracursos.literAlura.service.ConsumoAPI;
import com.aluracursos.literAlura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {
//    public Principal(SerieRepository repository) {
//        this.repositorio = repository;
//    }

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private IAutorRepository autor_repository;
    private ILibroRepository libro_repository;
    private List<DatosLibro> datos;

    public Principal(IAutorRepository a_r, ILibroRepository l_r) {
        this.autor_repository = a_r;
        this.libro_repository = l_r;

    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                1 - Buscar libro por título.
                2 - Listar libros registrados.
                3 - Listar autores registrados.
                4 - Explora autores por aino de actividad
                5 - Listar libros por idioma
                0 - Salir
                """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibros();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAnio();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibros() {
        List<DatosLibro> datos = getDatosLibro();

        if (datos == null || datos.isEmpty()) {
            System.out.println("No se encontró ningún libro con ese título.");
            return;
        }

        // Solo tomamos el primer resultado
        DatosLibro d = datos.get(0);

        if (!libro_repository.findByTituloContainsIgnoreCase(d.titulo()).isEmpty()) {
            System.out.println("El libro ya está en el catálogo.");
            return;
        }

        DatosAutor datosAutor = d.autores().get(0);
        Autor autor = autor_repository.findByNombre(datosAutor.nombre())
                .orElseGet(() -> autor_repository.save(new Autor(datosAutor)));

        // 3. Crear el libro y asignarle el autor
        Libro libro = new Libro(d);
        libro.setAutor(autor);

        // 4. Guardar en la BD ← esto es lo que faltaba
        libro_repository.save(libro);

        System.out.println("\n===== LIBRO ENCONTRADO =====");
        System.out.println("Título    : " + d.titulo());
        System.out.println("Autor     : " + getPrimerAutor(d));
        System.out.println("Idioma    : " + d.listaLenguaje());
        System.out.println("Descargas : " + d.numeroDescargas());
        System.out.println("============================");
    }

    private List<DatosLibro> getDatosLibro() {

        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        DatosAPI datosAPI = conversor.obtenerDatos(json, DatosAPI.class);

        datosAPI.listaLibros();

        return datosAPI.listaLibros();
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libro_repository.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autor_repository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Ingresa el año que deseas consultar:");
        String entrada = teclado.nextLine().trim();

        int anio = Integer.parseInt(entrada);
        int anioActual = java.time.Year.now().getValue();

        if (anio < 1 || anio > anioActual) {
            System.out.println("Ingresa un año entre 1 y " + anioActual + ".");
            return;
        }
        List<Autor> autores = autor_repository.autoresVivos(anio);
        autores.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Selecciona un idioma:
                en - Inglés
                es - Español
                fr - Francés
                pt - Portugués
                """);
        String idioma = teclado.nextLine().trim().toLowerCase();

        if (!List.of("en", "es", "fr", "pt").contains(idioma)) {
            System.out.println("Idioma no reconocido. Usa: en, es, fr o pt.");
            return;
        }

        List<Libro> libros = libro_repository.librosRegistradoPorLenguaje(Lenguaje.deTexto(idioma));
        libros.forEach(System.out::println);
    }


    private String getPrimerAutor(DatosLibro libro) {
        if (libro.autores() != null && !libro.autores().isEmpty()) {
            return libro.autores().get(0).nombre();
        }
        return "Desconocido";
    }

    // Obtiene el primer idioma o "N/A"
    private String getPrimerIdioma(DatosLibro libro) {
        if (libro.listaLenguaje() != null && !libro.listaLenguaje().isEmpty()) {
            return libro.listaLenguaje().get(0);
        }
        return "N/A";
    }


}
