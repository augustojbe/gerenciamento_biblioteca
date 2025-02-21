package com.example.library.dto;

import com.example.library.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "Campo obrigatorio")
        String isbn,

        @NotBlank(message = "Campo obrigatorio")
        String titulo,

        @NotNull(message = "Campo obrigatorio")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataPublicacao,

        GeneroLivro genero,

        BigDecimal preco,
        @NotNull(message = "Campo obrigatorio")
        UUID idAutor
) {
}
