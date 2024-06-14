package br.unitins.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.EmprestimoDTO;
import br.unitins.dto.EmprestimoResponseDTO;
import br.unitins.model.Emprestimo;
import br.unitins.model.Livro;
import br.unitins.model.Usuario;
import br.unitins.repository.EmprestimoRepository;
import br.unitins.repository.LivroRepository;
import br.unitins.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EmprestimoServiceImpl implements EmprestimoService {

    @Inject
    EmprestimoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    LivroRepository livroRepository;

    @Override
    @Transactional
    public EmprestimoResponseDTO insert(EmprestimoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Livro livro = livroRepository.findById(dto.livroId());
        if (livro == null) {
            throw new NotFoundException("Livro não encontrado");
        }

        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setLivro(livro);
        novoEmprestimo.setDataEmprestimo(dto.dataEmprestimo());
        novoEmprestimo.setDataDevolucao(dto.dataDevolucao());
        novoEmprestimo.setDataDevolucaoReal(dto.dataDevolucaoReal());
        novoEmprestimo.setValorMulta(dto.valorMulta());
        
        repository.persist(novoEmprestimo);
        return EmprestimoResponseDTO.valueOf(novoEmprestimo);
    }

    @Override
    @Transactional
    public EmprestimoResponseDTO update(EmprestimoDTO dto, Long id) {
        Emprestimo emprestimo = repository.findById(id);
        if (emprestimo == null) {
            throw new NotFoundException("Empréstimo não encontrado");
        }

        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Livro livro = livroRepository.findById(dto.livroId());
        if (livro == null) {
            throw new NotFoundException("Livro não encontrado");
        }

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(dto.dataEmprestimo());
        emprestimo.setDataDevolucao(dto.dataDevolucao());
        emprestimo.setDataDevolucaoReal(dto.dataDevolucaoReal());
        emprestimo.setValorMulta(dto.valorMulta());

        return EmprestimoResponseDTO.valueOf(emprestimo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public EmprestimoResponseDTO findById(Long id) {
        Emprestimo emprestimo = repository.findById(id);
        if (emprestimo == null) {
            throw new NotFoundException("Empréstimo não encontrado");
        }
        return EmprestimoResponseDTO.valueOf(emprestimo);
    }

    @Override
    @Transactional
    public List<EmprestimoResponseDTO> findByUsuarioId(Long usuarioId) {
        return repository.find("usuario.id = ?1", usuarioId).stream()
                .map(EmprestimoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<EmprestimoResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(EmprestimoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    // Método para calcular e atualizar a multa (exemplo)
    @Transactional
    public void calcularMulta(Long emprestimoId) {
        Emprestimo emprestimo = repository.findById(emprestimoId);
        if (emprestimo == null) {
            throw new NotFoundException("Empréstimo não encontrado");
        }

        LocalDate dataDevolucao = emprestimo.getDataDevolucao();
        LocalDate dataDevolucaoReal = emprestimo.getDataDevolucaoReal();

        if (dataDevolucaoReal != null && dataDevolucaoReal.isAfter(dataDevolucao)) {
            long diasAtraso = dataDevolucaoReal.toEpochDay() - dataDevolucao.toEpochDay();
            double valorMulta = diasAtraso * 1.5; // Exemplo: R$ 1,50 por dia de atraso
            emprestimo.setValorMulta(valorMulta);
        }
    }
}
