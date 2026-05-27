package com.francisco_montalvao.gprodutos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter


@Entity
@Table(name = "tbl_categoria")
public class Categoria {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(unique = true)
	private String nome;

	@CreationTimestamp
	private LocalDateTime criadoEm;


	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtoList;

}
