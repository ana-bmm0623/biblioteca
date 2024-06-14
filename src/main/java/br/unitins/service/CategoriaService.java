// CategoriaService.java
package br.unitins.service;

import br.unitins.dto.CategoriaDTO;
import br.unitins.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    CategoriaResponseDTO insert(CategoriaDTO dto);
    CategoriaResponseDTO update(CategoriaDTO dto, Long id);
    void delete(Long id);
    CategoriaResponseDTO findById(Long id);
    List<CategoriaResponseDTO> findByNome(String nome);
    List<CategoriaResponseDTO> findAll();
}
