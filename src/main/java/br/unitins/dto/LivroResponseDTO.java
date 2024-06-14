package br.unitins.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.model.Livro;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String isbn,
        int anoPublicacao,
        int edicao,
        int quantidadeDisponivel,
        int quantidadeTotal,
        String sinopse,
        AutorResponseDTO autor,
        EditoraResponseDTO editora,
        List<CategoriaResponseDTO> categorias // Correção: List<CategoriaResponseDTO>
) {

    public static LivroResponseDTO valueOf(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getEdicao(),
                livro.getQuantidadeDisponivel(),
                livro.getQuantidadeTotal(),
                livro.getSinopse(),
                AutorResponseDTO.valueOf(livro.getAutor()), 
                EditoraResponseDTO.valueOf(livro.getEditora()), 
                livro.getCategorias().stream().map(CategoriaResponseDTO::valueOf).collect(Collectors.toList())
);
    }
}
