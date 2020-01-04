package com.carros.domain;

import javax.persistence.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter @Setter
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String tipo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Long getId() {
		return id;
	}
	
	
}
