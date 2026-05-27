package com.francisco_montalvao.gprodutos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(


		@NotBlank(message = "Nome nao pode estar vazio")
		@Size(min = 4, message = "Nome deve ter pelo menos 4 letras")
		String nome
) {
}
