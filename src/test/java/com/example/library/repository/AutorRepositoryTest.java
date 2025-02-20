package com.example.library.repository;

import com.example.library.model.Autor;
import com.example.library.model.GeneroLivro;
import com.example.library.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livrorepository;

    @Test
    public void salvarTeste(){
        Autor autor = new Autor();
        autor.setNome("Augusto");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1988, 1, 29));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);

    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("15f6d7ed-b9d4-46c5-8920-7344db5c9726");

        Optional<Autor> posivelAutor = repository.findById(id);

        if(posivelAutor.isPresent()){

            Autor autoEncontrado = posivelAutor.get();
            System.out.println("Dados do Autor");
            System.out.println(autoEncontrado);

            autoEncontrado.setNacionalidade("Brasileiro");
            autoEncontrado.setNome("Augusto Alves");

            repository.save(autoEncontrado);
        }

    }

    @Test
    public void listarTeste(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void counTest(){
        var contagem = repository.count();
        System.out.println("Contagem de autores: " + contagem );
    }

    @Test
    public void delePorIdTeste(){
        var id = UUID.fromString("9e16535b-5d5b-4244-8361-77ca84903056");
        repository.deleteById(id);

    }

    @Test
    public void deleteTeste(){
        var id = UUID.fromString("15f6d7ed-b9d4-46c5-8920-7344db5c9726");
        var autor = repository.findById(id).get();
        repository.delete(autor);

    }

    @Test
    void salvarAutorComLivro(){

        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1988, 1, 29));

        Livro livro = new Livro();
        livro.setIsbn("2121121274");
        livro.setPreco(BigDecimal.valueOf(98));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Os puritanos");
        livro.setDataPublicacao(LocalDate.of(1970, 12, 25));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("2222121274");
        livro2.setPreco(BigDecimal.valueOf(125));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("Teologia dos Puritanos");
        livro2.setDataPublicacao(LocalDate.of(1990, 12, 24));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livrorepository.saveAll(autor.getLivros());

    }

    @Test
    void salvarAutorComLivroCascede(){

        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1988, 1, 29));

        Livro livro = new Livro();
        livro.setIsbn("2121121274");
        livro.setPreco(BigDecimal.valueOf(98));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Os puritanos");
        livro.setDataPublicacao(LocalDate.of(1970, 12, 25));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("2222121274");
        livro2.setPreco(BigDecimal.valueOf(125));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("Teologia dos Puritanos");
        livro2.setDataPublicacao(LocalDate.of(1990, 12, 24));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livrorepository.saveAll(autor.getLivros());

    }

    @Test
    void listarLivrosAutorTest(){
        var id = UUID.fromString("700ec53b-42b5-4a86-8519-5caa5b0611c9");
        var autor = repository.findById(id).get();

        List<Livro> livroListas = livrorepository.findByAutor(autor);
        autor.setLivros(livroListas);

        autor.getLivros().forEach(System.out::println);
    }



}
