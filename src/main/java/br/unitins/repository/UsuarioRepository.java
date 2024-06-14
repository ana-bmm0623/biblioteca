package br.unitins.repository;

import br.unitins.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findById(Long id) {
        return find("id", id).firstResult();
    }

    public Usuario findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Usuario findBySenha(String senha) {
        return find("senha", senha).firstResult();
    }
}
