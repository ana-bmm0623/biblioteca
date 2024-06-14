
package br.unitins.model;

public enum StatusUsuario {
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    private final Integer id;
    private final String label;

    private StatusUsuario(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusUsuario valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (StatusUsuario statususuario : StatusUsuario.values()) {
            if (statususuario.getId().equals(id))
                return statususuario;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}
