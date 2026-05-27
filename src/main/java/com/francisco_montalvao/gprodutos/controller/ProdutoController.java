package com.francisco_montalvao.gprodutos.controller;

import com.francisco_montalvao.gprodutos.dto.request.ProdutoRequestDTO;
import com.francisco_montalvao.gprodutos.dto.response.ProdutoResponseDTO;
import com.francisco_montalvao.gprodutos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	private final ProdutoService service;


	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> cadastrarProduto (@RequestBody @Valid ProdutoRequestDTO dto){
		var response = service.cadastrarProduto(dto);

		return ResponseEntity.created(URI.create("/produtos/" + response.id())).body(response);
	}


	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> listarTodosProdutos(){
		return ResponseEntity.ok().body(service.listarTodosProdutos());

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable("id") Long id){

		return ResponseEntity.ok().body(service.buscarProdutoPorId(id));
	}


	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoRequestDTO dto){
		var resp = service.atualizarProduto(id, dto);

		return ResponseEntity.ok(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> apagarProdutoPorId(@PathVariable("id") Long id){
		service.apagarProdutoPorId(id);

		return ResponseEntity.noContent().build();
	}

}
