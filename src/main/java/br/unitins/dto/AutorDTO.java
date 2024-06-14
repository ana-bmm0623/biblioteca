package br.unitins.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record AutorDTO(
        Long id,
        @NotBlank(message = "O nome do autor é obrigatório") String nome,
        String nacionalidade,
        LocalDate dataNascimento,
        String biografia) {
}
