package br.unitins.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.CategoriaDTO;
import br.unitins.dto.CategoriaResponseDTO;
import br.unitins.model.Categoria;
import br.unitins.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository repository;

    @Override
    @Transactional
    public CategoriaResponseDTO insert(CategoriaDTO dto) {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(dto.nome());
        repository.persist(novaCategoria);
        return CategoriaResponseDTO.valueOf(novaCategoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(CategoriaDTO dto, Long id) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada");
        }

        categoria.setNome(dto.nome());
        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada");
        }
        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    @Transactional
    public List<CategoriaResponseDTO> findByNome(String nome) {
        return repository.find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").stream()
                .map(CategoriaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CategoriaResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(CategoriaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
