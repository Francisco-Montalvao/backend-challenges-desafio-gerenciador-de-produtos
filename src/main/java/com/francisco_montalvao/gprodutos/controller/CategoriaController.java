package com.francisco_montalvao.gprodutos.controller;

import com.francisco_montalvao.gprodutos.dto.request.CategoriaRequestDTO;
import com.francisco_montalvao.gprodutos.dto.response.CategoriaResponseDTO;
import com.francisco_montalvao.gprodutos.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> cadastrarCategoria(@RequestBody @Valid CategoriaRequestDTO dto) {
        var response = service.cadastrarCategoria(dto);

        return ResponseEntity.created(URI.create("/categoria/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarTodasAsCategorias() {

        return ResponseEntity.ok().body(service.listarTodasCategorias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        service.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
