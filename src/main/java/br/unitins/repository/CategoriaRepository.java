package br.unitins.repository;



import br.unitins.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
    public Categoria findById(Long id) {
        return find("id", id).firstResult();
    }

    public Categoria findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}
