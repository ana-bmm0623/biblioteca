package br.unitins.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.dto.LivroDTO;
import br.unitins.dto.LivroResponseDTO;
import br.unitins.model.Autor;
import br.unitins.model.Categoria;
import br.unitins.model.Editora;
import br.unitins.model.Livro;
import br.unitins.repository.AutorRepository;
import br.unitins.repository.CategoriaRepository;
import br.unitins.repository.EditoraRepository;
import br.unitins.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroServiceImpl implements LivroService {

    @Inject
    LivroRepository repository;

    @Inject
    AutorRepository autorRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public LivroResponseDTO insert(LivroDTO dto) {
        Autor autor = autorRepository.findById(dto.autorId());
        if (autor == null) {
            throw new NotFoundException("Autor não encontrado");
        }

        Editora editora = editoraRepository.findById(dto.editoraId());
        if (editora == null) {
            throw new NotFoundException("Editora não encontrada");
        }

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(dto.titulo());
        novoLivro.setIsbn(dto.isbn());
        novoLivro.setAnoPublicacao(dto.anoPublicacao());
        novoLivro.setEdicao(dto.edicao());
        novoLivro.setQuantidadeDisponivel(dto.quantidadeDisponivel());
        novoLivro.setQuantidadeTotal(dto.quantidadeTotal());
        novoLivro.setSinopse(dto.sinopse());
        novoLivro.setAutor(autor);
        novoLivro.setEditora(editora);
        Set<Categoria> categorias = new HashSet<>();
        for (Long categoriaId : dto.categoriasIds()) {
            Categoria categoria = categoriaRepository.findById(categoriaId);
            if (categoria == null) {
                throw new NotFoundException("Categoria não encontrada: " + categoriaId);
            }
            categorias.add(categoria);
        }
        novoLivro.setCategorias(categorias);

        repository.persist(novoLivro);
        return LivroResponseDTO.valueOf(novoLivro);
    }

    @Override
    @Transactional
    public LivroResponseDTO update(LivroDTO dto, Long id) {
        Livro livro = repository.findById(id);
        if (livro == null) {
            throw new NotFoundException("Livro não encontrado");
        }

        Autor autor = autorRepository.findById(dto.autorId());
        if (autor == null) {
            throw new NotFoundException("Autor não encontrado");
        }

        Editora editora = editoraRepository.findById(dto.editoraId());
        if (editora == null) {
            throw new NotFoundException("Editora não encontrada");
        }

        // Mapear categorias
        Set<Categoria> categorias = new HashSet<>();
        for (Long categoriaId : dto.categoriasIds()) {
            Categoria categoria = categoriaRepository.findById(categoriaId);
            if (categoria == null) {
                throw new NotFoundException("Categoria não encontrada: " + categoriaId);
            }
            categorias.add(categoria);
        }

        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setEdicao(dto.edicao());
        livro.setQuantidadeDisponivel(dto.quantidadeDisponivel());
        livro.setQuantidadeTotal(dto.quantidadeTotal());
        livro.setSinopse(dto.sinopse());
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setCategorias(categorias);

        return LivroResponseDTO.valueOf(livro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public LivroResponseDTO findById(Long id) {
        Livro livro = repository.findById(id);
        if (livro == null) {
            throw new NotFoundException("Livro não encontrado");
        }
        return LivroResponseDTO.valueOf(livro);
    }

    @Override
    @Transactional
    public List<LivroResponseDTO> findByTitulo(String titulo) {
        return repository.find("UPPER(titulo) LIKE ?1", "%" + titulo.toUpperCase() + "%").stream()
                .map(LivroResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<LivroResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(LivroResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
