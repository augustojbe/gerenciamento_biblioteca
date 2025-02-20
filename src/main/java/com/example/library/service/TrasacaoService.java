package com.example.library.service;

import com.example.library.model.Autor;
import com.example.library.model.GeneroLivro;
import com.example.library.model.Livro;
import com.example.library.repository.AutorRepository;
import com.example.library.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TrasacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("b8b13211-dda2-41f2-8134-59d8a995dcf0"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2025, 01, 29));

        //livroRepository.save(livro);
    }

    @Transactional
    public void executar(){

        // Salva Autor
        Autor autor = new Autor();
        autor.setNome("Francisca");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1955, 1, 29));

        autorRepository.save(autor);

        // Salva Livro
        Livro livro = new Livro();
        livro.setIsbn("292121274");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Cobra azul");
        livro.setDataPublicacao(LocalDate.of(1980, 12, 24));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Francisca")){
            throw new RuntimeException("Rollback");
        }

    }






}
