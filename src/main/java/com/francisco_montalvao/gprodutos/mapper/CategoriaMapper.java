package com.francisco_montalvao.gprodutos.mapper;

import com.francisco_montalvao.gprodutos.dto.request.CategoriaRequestDTO;
import com.francisco_montalvao.gprodutos.dto.response.CategoriaResponseDTO;
import com.francisco_montalvao.gprodutos.model.Categoria;
import org.springframework.stereotype.Component;


@Component
public class CategoriaMapper {

	public Categoria toEntity(CategoriaRequestDTO dto){
		Categoria categoria = new Categoria();
		categoria.setNome(dto.nome());

		return categoria;
	}

	public CategoriaResponseDTO toDto(Categoria categoria){
		return new CategoriaResponseDTO(categoria.getId(), categoria.getNome(), categoria.getCriadoEm());
	}
}
