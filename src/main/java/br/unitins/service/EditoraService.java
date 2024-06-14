// EditoraService.java
package br.unitins.service;

import java.util.List;

import br.unitins.dto.EditoraDTO;
import br.unitins.dto.EditoraResponseDTO;

public interface EditoraService {
    EditoraResponseDTO insert(EditoraDTO dto);

    EditoraResponseDTO update(EditoraDTO dto, Long id);

    void delete(Long id);

    EditoraResponseDTO findById(Long id);

    List<EditoraResponseDTO> findByNome(String nome);

    List<EditoraResponseDTO> findAll();
}
