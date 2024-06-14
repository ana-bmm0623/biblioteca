package br.unitins.model;

public enum TipoUsuario {
    ALUNO(1, "Aluno"),
    PROFESSOR(2, "Professor"),
    FUNCIONARIO(3, "Funcionário"),
    ADMINISTRADOR(4, "Administrador");

    private final Integer id;
    private final String label;

    private TipoUsuario(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoUsuario valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoUsuario tipousuario : TipoUsuario.values()) {
            if (tipousuario.getId().equals(id))
                return tipousuario;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}
