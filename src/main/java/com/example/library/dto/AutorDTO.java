package com.example.library.dto;

import com.example.library.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;


public record AutorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 100, min = 2, message = "Campo fora do padrão")
        String nome,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, min = 2, message = "Campo fora do padrão")
        String nacionalidade) {

    public Autor maperarParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
