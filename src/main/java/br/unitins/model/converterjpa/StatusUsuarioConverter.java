package br.unitins.model.converterjpa;

import br.unitins.model.StatusUsuario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusUsuarioConverter implements AttributeConverter<StatusUsuario, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusUsuario statususuario) {
        return (statususuario == null ? null : statususuario.getId());
    }

    @Override
    public StatusUsuario convertToEntityAttribute(Integer id) {
        return StatusUsuario.valueOf(id);
    }

}
