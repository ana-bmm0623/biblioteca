package br.unitins.repository;

import br.unitins.model.Autor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutorRepository implements PanacheRepository<Autor> {
    public Autor findById(Long id) {
        return find("id", id).firstResult();
    }

    public Autor findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public Autor findByNacionalidade(String nacionalidade) {
        return find("nacionalidade", nacionalidade).firstResult();
    }

    public Autor findByDataNascimento(String dataNascimento) {
        return find("dataNascimento", dataNascimento).firstResult();
    }

    public Autor findByBiografia(String biografia) {
        return find("biografia", biografia).firstResult();
    }
}
