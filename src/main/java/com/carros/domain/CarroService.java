package com.carros.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarroService {
	public List<Carro> getCarros() {
		List<Carro> carros = new ArrayList<Carro>();
		
		carros.add(new Carro(1L,"Fusca"));
		carros.add(new Carro(2L,"Chevette"));
		carros.add(new Carro(3L,"Ferrari"));
		
		return carros;		
	}
	
}
