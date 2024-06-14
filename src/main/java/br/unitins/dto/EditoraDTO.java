package br.unitins.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditoraDTO(
        Long id,
        @NotBlank(message = "O nome da editora é obrigatório") String nome,
        @NotBlank(message = "O CNPJ da editora é obrigatório") String cnpj,
        String endereco,
        String telefone,
        @Email(message = "Email inválido") String email) {
}
