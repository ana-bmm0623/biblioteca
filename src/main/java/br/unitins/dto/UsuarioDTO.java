package br.unitins.dto;

import br.unitins.model.StatusUsuario;
import br.unitins.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "O nome do usuário é obrigatório") String nome,
        @NotBlank(message = "O email do usuário é obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "A senha do usuário é obrigatória") String senha,
        @NotNull(message = "O tipo de usuário é obrigatório") TipoUsuario tipoUsuario,
        StatusUsuario status,
        String endereco,
        String telefone) {

   
}
