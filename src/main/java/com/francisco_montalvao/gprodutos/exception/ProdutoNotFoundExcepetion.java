package com.francisco_montalvao.gprodutos.exception;

import java.util.NoSuchElementException;

public class ProdutoNotFoundExcepetion extends NoSuchElementException {
	public ProdutoNotFoundExcepetion(String mensagem){
		super(mensagem);
	}
}
