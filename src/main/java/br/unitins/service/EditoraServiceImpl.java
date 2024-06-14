package br.unitins.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.EditoraDTO;
import br.unitins.dto.EditoraResponseDTO;
import br.unitins.model.Editora;
import br.unitins.repository.EditoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EditoraServiceImpl implements EditoraService {

    @Inject
    EditoraRepository repository;

    @Override
    @Transactional
    public EditoraResponseDTO insert(EditoraDTO dto) {
        Editora novaEditora = new Editora();
        novaEditora.setNome(dto.nome());
        novaEditora.setCnpj(dto.cnpj());
        novaEditora.setEndereco(dto.endereco());
        novaEditora.setTelefone(dto.telefone());
        novaEditora.setEmail(dto.email());
        repository.persist(novaEditora);
        return EditoraResponseDTO.valueOf(novaEditora);
    }

    @Override
    @Transactional
    public EditoraResponseDTO update(EditoraDTO dto, Long id) {
        Editora editora = repository.findById(id);
        if (editora == null) {
            throw new NotFoundException("Editora não encontrada");
        }

        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEndereco(dto.endereco());
        editora.setTelefone(dto.telefone());
        editora.setEmail(dto.email());
        repository.persist(editora);
        return EditoraResponseDTO.valueOf(editora);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var editora = repository.findByIdOptional(id);
        if (editora.isPresent()) {
            repository.delete(editora.get());
        } else {
            throw new NotFoundException("Editora não encontrada com o ID: " + id); // Ou outra forma de tratamento de erro
        }
    }
    
    @Override
    @Transactional
    public EditoraResponseDTO findById(Long id) {
        Editora editora = repository.findById(id);
        if (editora == null) {
            throw new NotFoundException("Editora não encontrada");
        }
        return EditoraResponseDTO.valueOf(editora);
    }

    @Override
    @Transactional
    public List<EditoraResponseDTO> findByNome(String nome) {
        return repository.find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").stream()
                .map(EditoraResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<EditoraResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(EditoraResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
    
}
