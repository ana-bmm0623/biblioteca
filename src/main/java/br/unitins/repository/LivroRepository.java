package br.unitins.repository;

import br.unitins.model.Livro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LivroRepository implements PanacheRepository<Livro> {
    public Livro findById(Long id) {
        return find("id", id).firstResult();
    }

    public Livro findByTitulo(String titulo) {
        return find("titulo", titulo).firstResult();
    }

    public Livro findByAnoPublicacao(String anoPublicacao) {
        return find("anoPublicacao", anoPublicacao).firstResult();
    }

    public Livro findByEdicao(String edicao) {
        return find("edicao", edicao).firstResult();
    }

    public Livro findByNumeroPaginas(String numeroPaginas) {
        return find("numeroPaginas", numeroPaginas).firstResult();
    }

    public Livro findByIsbn(String isbn) {
        return find("isbn", isbn).firstResult();
    }

    public Livro findByEditora(String editora) {
        return find("editora", editora).firstResult();
    }

    public Livro findByCategoria(String categoria) {
        return find("categoria", categoria).firstResult();
    }

    public Livro findByAutor(String autor) {
        return find("autor", autor).firstResult();
    }
}
