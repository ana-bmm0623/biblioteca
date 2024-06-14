package br.unitins.service;

import java.util.List;

import br.unitins.dto.AutorDTO;
import br.unitins.dto.AutorResponseDTO;

public interface AutorService {
    AutorResponseDTO insert(AutorDTO dto);

    AutorResponseDTO update(AutorDTO dto, Long id);

    void delete(Long id);

    AutorResponseDTO findById(Long id);

    List<AutorResponseDTO> findByNome(String nome);

    List<AutorResponseDTO> findAll();
}
