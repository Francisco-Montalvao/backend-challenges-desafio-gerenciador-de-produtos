package com.francisco_montalvao.gprodutos.mapper;

import com.francisco_montalvao.gprodutos.dto.request.ProdutoRequestDTO;
import com.francisco_montalvao.gprodutos.dto.response.CategoriaResponseDTO;
import com.francisco_montalvao.gprodutos.dto.response.ProdutoResponseDTO;
import com.francisco_montalvao.gprodutos.model.Categoria;
import com.francisco_montalvao.gprodutos.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {


	public Produto toEntity (ProdutoRequestDTO dto, Categoria categoria){
		Produto produto = new Produto();

		produto.setNome(dto.nome());
		produto.setDescricao(dto.descricao());
		produto.setPreco(dto.preco());
		produto.setEstoque(dto.estoque());
		produto.setCategoria(categoria);

		return produto;
	}

	public ProdutoResponseDTO toDto (Produto produto){
		return new ProdutoResponseDTO(
				produto.getId(),
				produto.getNome(),
				produto.getDescricao(),
				produto.getPreco(),
				produto.getEstoque(),
				new CategoriaResponseDTO(produto.getCategoria().getId(), produto.getCategoria().getNome(), null),
				produto.getCriadoEm(),
				produto.getAtualizadoEm()
		);

	}

	public Produto updateEntityFromDto(Produto produto, Categoria categoria, ProdutoRequestDTO dto){

		produto.setNome(dto.nome());
		produto.setDescricao(dto.descricao());
		produto.setPreco(dto.preco());
		produto.setCategoria(categoria);
		produto.setEstoque(dto.estoque());

		return produto;
	}
}
