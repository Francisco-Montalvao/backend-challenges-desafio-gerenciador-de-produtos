package com.francisco_montalvao.gprodutos.repository;

import com.francisco_montalvao.gprodutos.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	boolean existsByNome(@NotBlank
			                     (message = "Nome nao pode estar vazio") @Size(min = 4, message = "Nome deve ter pelo menos 3 letras") String nome);
}
