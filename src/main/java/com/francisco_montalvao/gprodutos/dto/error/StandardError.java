package com.francisco_montalvao.gprodutos.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

public record StandardError (
		LocalDateTime timestamp,
		Integer status,
		String mensagem,
		@JsonInclude(JsonInclude.Include.NON_NULL)
		List<ErrorDetalhe> erros


){
}
