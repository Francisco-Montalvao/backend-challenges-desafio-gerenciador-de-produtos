package com.francisco_montalvao.gprodutos.exception;


import org.springframework.dao.DataIntegrityViolationException;


public class NomeJaExistenteException extends DataIntegrityViolationException {

	public NomeJaExistenteException(String message) {
		super(message);
	}
}
