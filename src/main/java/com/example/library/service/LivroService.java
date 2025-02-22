package com.example.library.service;

import com.example.library.model.Livro;
import com.example.library.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;


    public Livro salvar(Livro livro) {
       return repository.save(livro);
    }
}
