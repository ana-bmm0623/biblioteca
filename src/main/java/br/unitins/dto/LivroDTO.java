package br.unitins.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record LivroDTO(
        Long id,
        @NotBlank(message = "O título do livro é obrigatório") String titulo,
        @NotBlank(message = "O ISBN do livro é obrigatório") String isbn,
        @Positive(message = "O ano de publicação deve ser positivo") int anoPublicacao,
        @PositiveOrZero(message = "A edição deve ser positiva ou zero") int edicao,
        @PositiveOrZero(message = "A quantidade disponível deve ser positiva ou zero") int quantidadeDisponivel,
        @Positive(message = "A quantidade total deve ser positiva") int quantidadeTotal,
        @NotBlank(message = "A sinopse do livro é obrigatória") String sinopse,
        @NotNull(message = "O autor do livro é obrigatório") Long autorId,
        @NotNull(message = "A editora do livro é obrigatória") Long editoraId,
        List<Long> categoriasIds) {
}
