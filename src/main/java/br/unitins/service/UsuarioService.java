// UsuarioService.java
package br.unitins.service;

import java.util.List;

import br.unitins.dto.UsuarioDTO;
import br.unitins.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO insert(UsuarioDTO dto);

    UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    UsuarioResponseDTO findByEmail(String email);

    List<UsuarioResponseDTO> findAll();
}
