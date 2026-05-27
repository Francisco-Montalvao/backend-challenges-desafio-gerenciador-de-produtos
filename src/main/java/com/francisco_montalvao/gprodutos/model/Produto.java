package com.francisco_montalvao.gprodutos.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter



@Entity
@Table(name = "tbl_produto")
public class Produto {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nome;


	private String descricao;




	private BigDecimal preco;


	@Column(nullable = false)
	private Integer estoque;


	@CreationTimestamp
	private LocalDateTime criadoEm;

	@UpdateTimestamp
	private LocalDateTime atualizadoEm;


	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

}
