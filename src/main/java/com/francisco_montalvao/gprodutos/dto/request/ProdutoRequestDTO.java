package com.francisco_montalvao.gprodutos.dto.request;



import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

		@NotBlank(message = "Nao pode estar vazio")
		String nome,


		String descricao,

		@Positive(message = "preço tem que ser maior que zeo ")
		BigDecimal preco,

		@PositiveOrZero(message = "estoque nao pode ser negativo")
		Integer estoque,

		@NotNull(message = "Id categoria nao pode estar em branco")
		Long categoria_id
) {
}
