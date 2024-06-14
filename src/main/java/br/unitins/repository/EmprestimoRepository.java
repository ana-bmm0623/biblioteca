package br.unitins.repository;

import br.unitins.model.Emprestimo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmprestimoRepository implements PanacheRepository<Emprestimo> {
    public Emprestimo findById(Long id) {
        return find("id", id).firstResult();
    }

    public Emprestimo findByDataEmprestimo(String dataEmprestimo) {
        return find("dataEmprestimo", dataEmprestimo).firstResult();
    }

    public Emprestimo findByDataDevolucao(String dataDevolucao) {
        return find("dataDevolucao", dataDevolucao).firstResult();
    }

    public Emprestimo findByLivro(String livro) {
        return find("livro", livro).firstResult();
    }

    public Emprestimo findByUsuario(String usuario) {
        return find("usuario", usuario).firstResult();
    }

    public Emprestimo findByStatus(String status) {
        return find("status", status).firstResult();
    }
}
