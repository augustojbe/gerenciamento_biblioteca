package com.example.library.controller.mappers;

import com.example.library.controller.dto.AutorDTO;
import com.example.library.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
