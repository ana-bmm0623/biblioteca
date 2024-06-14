package br.unitins.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.AutorDTO;
import br.unitins.dto.AutorResponseDTO;
import br.unitins.model.Autor;
import br.unitins.repository.AutorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AutorServiceImpl implements AutorService {

    @Inject
    AutorRepository repository;

    @Override
    @Transactional
    public AutorResponseDTO insert(AutorDTO dto) {
        Autor novoAutor = new Autor();
        novoAutor.setNome(dto.nome());
        novoAutor.setNacionalidade(dto.nacionalidade());
        novoAutor.setDataNascimento(dto.dataNascimento());
        novoAutor.setBiografia(dto.biografia());
        repository.persist(novoAutor);
        return AutorResponseDTO.valueOf(novoAutor);
    }

    @Override
    @Transactional
    public AutorResponseDTO update(AutorDTO dto, Long id) {
        Autor autor = repository.findById(id);
        if (autor == null) {
            throw new NotFoundException("Autor não encontrado");
        }

        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());
        autor.setBiografia(dto.biografia());
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public AutorResponseDTO findById(Long id) {
        Autor autor = repository.findById(id);
        if (autor == null) {
            throw new NotFoundException("Autor não encontrado");
        }
        return AutorResponseDTO.valueOf(autor);
    }

    @Override
    @Transactional
    public List<AutorResponseDTO> findByNome(String nome) {
        return repository.find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").stream()
                .map(AutorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AutorResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(AutorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
