// LivroService.java
package br.unitins.service;

import java.util.List;

import br.unitins.dto.LivroDTO;
import br.unitins.dto.LivroResponseDTO;

public interface LivroService {
    LivroResponseDTO insert(LivroDTO dto);

    LivroResponseDTO update(LivroDTO dto, Long id);

    void delete(Long id);

    LivroResponseDTO findById(Long id);

    List<LivroResponseDTO> findByTitulo(String titulo);

    List<LivroResponseDTO> findAll();
}
