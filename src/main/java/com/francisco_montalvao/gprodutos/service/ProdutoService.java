package com.francisco_montalvao.gprodutos.service;


import com.francisco_montalvao.gprodutos.dto.request.ProdutoRequestDTO;
import com.francisco_montalvao.gprodutos.dto.response.ProdutoResponseDTO;
import com.francisco_montalvao.gprodutos.exception.CategoriaNotFoundException;
import com.francisco_montalvao.gprodutos.exception.NomeJaExistenteException;
import com.francisco_montalvao.gprodutos.exception.ProdutoNotFoundExcepetion;
import com.francisco_montalvao.gprodutos.mapper.ProdutoMapper;
import com.francisco_montalvao.gprodutos.model.Produto;
import com.francisco_montalvao.gprodutos.repository.CategoriaRepository;
import com.francisco_montalvao.gprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProdutoService {
	private final ProdutoRepository repository;
	private final ProdutoMapper mapper;
	private final CategoriaRepository categoriaRepository;


	@Transactional
	public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto){

		var categoria = categoriaRepository.findById(dto.categoria_id())
				.orElseThrow(
						()-> new CategoriaNotFoundException("Categoria com id " +
								dto.categoria_id() + " nao cadastrada"));

		if (repository.existsByNome(dto.nome())){
			throw new NomeJaExistenteException("Já existe um produto com o nome " + dto.nome());
		}

		Produto produto = mapper.toEntity(dto, categoria);
		Produto produtoSalvo = repository.save(produto);

		return mapper.toDto(produtoSalvo);
	}

	public List<ProdutoResponseDTO> listarTodosProdutos(){
		return repository.findAll()
				.stream()
				.map(mapper::toDto)
				.toList();
	}


	public ProdutoResponseDTO buscarProdutoPorId(Long id) {
		var response = repository.findById(id).orElseThrow(
				()-> new ProdutoNotFoundExcepetion("produto com "+ id + " nao encontrado"));
		return mapper.toDto(response);
	}


	@Transactional
	public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
		var produto = repository.findById(id).orElseThrow(
				()-> new ProdutoNotFoundExcepetion("produto com id "+ id + " nao existe")
		);

		var categoria = categoriaRepository.findById(dto.categoria_id()).orElseThrow(
				()-> new CategoriaNotFoundException("categoria com "+ dto.categoria_id() + " nao cadastrada")
		);

		produto = mapper.updateEntityFromDto(produto, categoria, dto);

		var produtoAtualizado = repository.save(produto);

		return mapper.toDto(produtoAtualizado);

	}

	@Transactional
	public void apagarProdutoPorId(Long id) {
		var produto = repository.findById(id).orElseThrow(
				()-> new ProdutoNotFoundExcepetion("produto com id "+ id + " nao existe")
		);

		repository.deleteById(id);
	}
}
