package com.francisco_montalvao.gprodutos.service;


import com.francisco_montalvao.gprodutos.dto.request.CategoriaRequestDTO;
import com.francisco_montalvao.gprodutos.dto.response.CategoriaResponseDTO;
import com.francisco_montalvao.gprodutos.exception.CategoriaNotFoundException;
import com.francisco_montalvao.gprodutos.exception.CategoriaPossuiProdutosException;
import com.francisco_montalvao.gprodutos.exception.NomeJaExistenteException;
import com.francisco_montalvao.gprodutos.mapper.CategoriaMapper;
import com.francisco_montalvao.gprodutos.model.Categoria;
import com.francisco_montalvao.gprodutos.repository.CategoriaRepository;
import com.francisco_montalvao.gprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriaService {
	private final CategoriaRepository repository;
	private final CategoriaMapper mapper;
	private final ProdutoRepository produtoRepository;



	// create
	public CategoriaResponseDTO cadastrarCategoria(CategoriaRequestDTO dto){

		Categoria categoria = mapper.toEntity(dto);

		if (repository.existsByNome(dto.nome())){
			throw new NomeJaExistenteException("Já existe uma categoria com o nome "+ dto.nome());
		}

		Categoria categoriaSalva = repository.save(categoria);

		return mapper.toDto(categoriaSalva);
	}

	// read
	public List<CategoriaResponseDTO> listarTodasCategorias(){
		return repository
				.findAll()
				.stream()
				//.map(categoria -> mapper.toDto(categoria))
				.map(mapper::toDto)
 				.toList();
	}

	//delete
	public void deletarCategoria(Long id){
		if (!repository.existsById(id)){
			throw new CategoriaNotFoundException("Categoria com id "+  id + " nao existe");
		}

		if (produtoRepository.existsByCategoriaId(id)){
			throw new CategoriaPossuiProdutosException("Categoria possui produtos vinculados");
		}
		repository.deleteById(id);
	}



}
