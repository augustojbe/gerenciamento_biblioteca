package com.example.library.repository;

import com.example.library.model.Autor;
import com.example.library.model.GeneroLivro;
import com.example.library.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    LivroRepository livrorepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTeste(){
        Livro livro = new Livro();
        livro.setIsbn("12121212");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("A cruz de cristo");
        livro.setDataPublicacao(LocalDate.of(1967, 12, 24));

        var id = UUID.fromString("4ec0435e-a08e-44bf-bd61-db57e7105f37");

        Autor autorEncontrado = autorRepository.findById(id).orElse(null);

        livro.setAutor(autorEncontrado);

        livrorepository.save(livro);

    }

    @Test
    void salvarAutorELivroTeste(){
        Livro livro = new Livro();
        livro.setIsbn("292121274");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Cobra azul");
        livro.setDataPublicacao(LocalDate.of(1980, 12, 24));

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1955, 1, 29));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livrorepository.save(livro);

    }

    @Test
    void salvarCascaideTeste(){
        Livro livro = new Livro();
        livro.setIsbn("12121274");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("O poder de Cristo");
        livro.setDataPublicacao(LocalDate.of(1967, 12, 24));

        Autor autor = new Autor();
        autor.setNome("Augusto");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1988, 1, 29));

        livro.setAutor(autor);

        livrorepository.save(livro);

    }

    @Test
    void atualizarAutorDoLivro(){

        var id = UUID.fromString("b3f8e7ca-2945-4351-bb91-99009fbb715f");
        var livroParaAtualizar = livrorepository.findById(id).orElse(null);

        var idAutor = UUID.fromString("ef4d834c-6c9a-4594-8cb0-bd6ada3ddb4a");
        Autor novoAutor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(novoAutor);


        livrorepository.save(livroParaAtualizar);

    }
    @Test
    void deletarPoId(){
        var id = UUID.fromString("b3f8e7ca-2945-4351-bb91-99009fbb715f");
        livrorepository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        var id = UUID.fromString("fc2b3234-02c9-47f7-b6e9-35cb7ad1aaf8");
        livrorepository.deleteById(id);

    }

    @Test
    @Transactional
    void buscarLivroTest(){
        var id = UUID.fromString("6dd09e87-fe84-4736-b575-96cdcaa5efa5");
        var livroEAutor = livrorepository.findById(id).orElse(null);

        System.out.println("Nome do Livro: " + livroEAutor.getTitulo());
        System.out.println("Nome do Autor: " + livroEAutor.getAutor().getNome());

    }

    @Test
    void porTituloTest(){
       List<Livro> lista = livrorepository.findByTitulo("A cruz de cristo");
       lista.forEach(System.out::println);
    }

    @Test
    void porIsbnTest(){
        List<Livro> lista = livrorepository.findByIsbn("2121121274");
        lista.forEach(System.out::println);
    }

    @Test
    void porTituloEndPreco(){
        var preco = BigDecimal.valueOf(100);
        var titulo = "A cruz de cristo";

        List<Livro> lista = livrorepository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void porTituloEndIsbn(){
        var isbn = "12121212";
        var titulo = "A cruz de cristo";

        List<Livro> lista = livrorepository.findByTituloAndIsbn(titulo, isbn);
        lista.forEach(System.out::println);
    }

    @Test
    void porAgumaPalavraNoTituloTest(){
        List<Livro> lista = livrorepository.findByTituloContaining("puritanos");
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivroComQueryJpql(){
        var resultado = livrorepository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = livrorepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTotulosdiferentesDeLivros(){
        var resultado = livrorepository.ListarNomeDiferentesLivros();
        resultado.forEach(System.out::println);
    }
    @Test
    void listarGeneroDeLivrosAutoresBrasileiros(){
        var resultado = livrorepository.ListarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = livrorepository.findByGenero(GeneroLivro.BIOGRAFIA, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPorParametros(){
        var resultado = livrorepository.findByGenero(GeneroLivro.BIOGRAFIA, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGenero(){
        livrorepository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacao(){
        livrorepository.updateDataPublicacao(LocalDate.of(2022, 10, 10));
    }








}
