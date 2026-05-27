package com.francisco_montalvao.gprodutos.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(
		Long id,
		String nome,
		String descricao,
		BigDecimal preco,
		Integer estoque,
		@JsonIgnoreProperties
		CategoriaResponseDTO categoria,
		LocalDateTime criadoEm,
		LocalDateTime atualizadoEm


) {
}
