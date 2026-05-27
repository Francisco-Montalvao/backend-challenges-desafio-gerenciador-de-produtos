package com.francisco_montalvao.gprodutos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public record CategoriaResponseDTO(
		Long id,
		String nome,
		@JsonInclude(JsonInclude.Include.NON_NULL)
		LocalDateTime criado_em

) {

}
