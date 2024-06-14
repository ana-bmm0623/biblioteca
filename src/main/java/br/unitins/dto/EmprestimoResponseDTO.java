package br.unitins.dto;

import java.time.LocalDate;

import br.unitins.model.Emprestimo;

public record EmprestimoResponseDTO(
        Long id,
        UsuarioResponseDTO usuario,
        LivroResponseDTO livro,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        LocalDate dataDevolucaoReal,
        Double valorMulta) {

    public static EmprestimoResponseDTO valueOf(Emprestimo emprestimo) {
        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                UsuarioResponseDTO.valueOf(emprestimo.getUsuario()),
                LivroResponseDTO.valueOf(emprestimo.getLivro()),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getDataDevolucaoReal(),
                emprestimo.getValorMulta());
    }
}
