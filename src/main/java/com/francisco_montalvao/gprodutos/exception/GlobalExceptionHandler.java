package com.francisco_montalvao.gprodutos.exception;

import com.francisco_montalvao.gprodutos.dto.error.ErrorDetalhe;
import com.francisco_montalvao.gprodutos.dto.error.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidacao (MethodArgumentNotValidException ex){
		StandardError error = new StandardError(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro de validação em campos",
				ex.getFieldErrors()
						.stream()
						.map(er -> new ErrorDetalhe(Objects.requireNonNull(er.getField()),er.getDefaultMessage()))
						.toList());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


	@ExceptionHandler(NomeJaExistenteException.class)
	public ResponseEntity<?> handleNomeDuplicadoProduto(NomeJaExistenteException ex){
		StandardError error = new StandardError(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				ex.getMessage(),
				null
		);

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}


	@ExceptionHandler(ProdutoNotFoundExcepetion.class)
	public ResponseEntity<StandardError> handleNaoEncontrado(ProdutoNotFoundExcepetion ne){
		StandardError error = new StandardError(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				ne.getMessage(),
				null
		);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(CategoriaPossuiProdutosException.class)
	public ResponseEntity<StandardError> categoriaExistente (CategoriaPossuiProdutosException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage(),
				null
		));
	}


	@ExceptionHandler(CategoriaNotFoundException.class)
	public ResponseEntity<StandardError> categoriaNaoCadastrada(CategoriaNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				null
		));
	}

}
