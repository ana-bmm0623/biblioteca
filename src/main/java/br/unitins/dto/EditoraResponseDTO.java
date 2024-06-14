package br.unitins.dto;

import br.unitins.model.Editora;

public record EditoraResponseDTO(
    Long id,
    String nome,
    String cnpj,
    String endereco,
    String telefone,
    String email
) {
    // Construtor para receber apenas o ID
    public EditoraResponseDTO(Long id) {
        this(id, null, null, null, null, null);
    }

    // Método valueOf para mapear da entidade Editora (corrigido)
    public static EditoraResponseDTO valueOf(Editora editora) {
        if (editora == null) {
            return null; // Ou lance uma exceção, dependendo da sua lógica
        }
        return new EditoraResponseDTO(
            editora.getId(),
            editora.getNome(),
            editora.getCnpj(),
            editora.getEndereco(),
            editora.getTelefone(),
            editora.getEmail()
        );
    }
}
