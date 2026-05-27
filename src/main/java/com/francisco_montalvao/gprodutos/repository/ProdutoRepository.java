package com.francisco_montalvao.gprodutos.repository;

import com.francisco_montalvao.gprodutos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	boolean existsByNome(String nome);

	Boolean existsByCategoriaId(Long id);
}
