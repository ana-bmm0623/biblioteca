package br.unitins.dto;

import br.unitins.model.StatusUsuario;
import br.unitins.model.TipoUsuario;
import br.unitins.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        TipoUsuario tipoUsuario,
        StatusUsuario status,
        String endereco,
        String telefone) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getStatus(),
                usuario.getEndereco(),
                usuario.getTelefone());
    }
}
