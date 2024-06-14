package br.unitins.dto;

import java.time.LocalDate;

import br.unitins.model.Autor;
import jakarta.validation.constraints.NotNull;

public record AutorResponseDTO(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento,
        String biografia) {

    public static AutorResponseDTO valueOf(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento(),
                autor.getBiografia());
    }

    public static AutorResponseDTO valueOf(@NotNull(message = "O autor do livro é obrigatório") Long autorId) {
        return new AutorResponseDTO(autorId, null, null, null, null);
    }
}
