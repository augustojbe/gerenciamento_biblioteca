package com.example.library.repository;

import com.example.library.service.TrasacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacaoServiceTest {

    @Autowired
    TrasacaoService trasacaoService;

    @Test
    void trasacaoSimples(){
        trasacaoService.executar();

    }

    @Test
    void trasacaoEstadoManaged(){
        trasacaoService.atualizarSemAtualizar();

    }


}
