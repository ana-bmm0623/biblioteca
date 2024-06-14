package br.unitins.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EmprestimoDTO(
        Long id,
        @NotNull(message = "O ID do usuário é obrigatório") Long usuarioId,
        @NotNull(message = "O ID do livro é obrigatório") Long livroId,
        LocalDate dataEmprestimo,
        @NotNull(message = "A data de devolução é obrigatória") LocalDate dataDevolucao,
        LocalDate dataDevolucaoReal,
        Double valorMulta) {
}
