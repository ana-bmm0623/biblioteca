package br.unitins.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.UsuarioDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.model.Usuario;
import br.unitins.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        // Criptografar a senha antes de salvar (use uma biblioteca adequada)
        novoUsuario.setSenha(dto.senha()); // Exemplo simplificado, sem criptografia
        novoUsuario.setTipoUsuario(dto.tipoUsuario());
        novoUsuario.setDataCadastro(LocalDate.now());
        novoUsuario.setStatus(dto.status());
        novoUsuario.setEndereco(dto.endereco());
        novoUsuario.setTelefone(dto.telefone());

        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        // Atualizar senha apenas se fornecida no DTO
        if (dto.senha() != null && !dto.senha().isEmpty()) {
            // Criptografar a nova senha (use uma biblioteca adequada)
            usuario.setSenha(dto.senha()); // Exemplo simplificado, sem criptografia
        }
        usuario.setTipoUsuario(dto.tipoUsuario());
        usuario.setStatus(dto.status());
        usuario.setEndereco(dto.endereco());
        usuario.setTelefone(dto.telefone());

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = repository.find("email", email).firstResult();
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public List<UsuarioResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(UsuarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
