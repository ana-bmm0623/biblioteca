package br.unitins.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.model.Categoria;

public record CategoriaResponseDTO(
        Long id,
        String nome) {
    public static CategoriaResponseDTO valueOf(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }

    public static List<CategoriaResponseDTO> valueOf(Set<Categoria> categorias) {
        return categorias.stream()
                .map(CategoriaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
