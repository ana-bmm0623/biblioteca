package br.unitins.repository;

import br.unitins.model.Editora;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EditoraRepository implements PanacheRepository<Editora> {

    public Editora findById(Long id) {
        return find("id", id).firstResult();
    }

    public Editora findByNome(String nome) {
        return find("nome", nome).firstResult();
    }

    public Editora findByCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }

    public Editora findByEndereco(String endereco) {
        return find("endereco", endereco).firstResult();
    }

    public Editora findByTelefone(String telefone) {
        return find("telefone", telefone).firstResult();
    }

    public Editora findByEmail(String email) {
        return find("email", email).firstResult();
    }

}
