// EmprestimoService.java
package br.unitins.service;

import java.util.List;

import br.unitins.dto.EmprestimoDTO;
import br.unitins.dto.EmprestimoResponseDTO;

public interface EmprestimoService {
    EmprestimoResponseDTO insert(EmprestimoDTO dto);

    EmprestimoResponseDTO update(EmprestimoDTO dto, Long id);

    void delete(Long id);

    EmprestimoResponseDTO findById(Long id);

    List<EmprestimoResponseDTO> findByUsuarioId(Long usuarioId);

    List<EmprestimoResponseDTO> findAll();
}
