package br.unitins.model.converterjpa;

import br.unitins.model.TipoUsuario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoUsuario tipousuario) {
        return (tipousuario == null ? null : tipousuario.getId());
    }

    @Override
    public TipoUsuario convertToEntityAttribute(Integer id) {
        return TipoUsuario.valueOf(id);
    }

}
