package com.carros.domain;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros() {
		List<Carro> carros = rep.findAll();
		List<CarroDTO> carrosDTO = new ArrayList<CarroDTO>();
		
		for (Carro c : carros) {
			carrosDTO.add(new CarroDTO(c));
		}
		
		return carrosDTO;
	}
	
	public Optional<CarroDTO> getById(long id) {
		return rep.findById(id).map(c -> new CarroDTO(c));
	}
	
	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
	}

	public CarroDTO save(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível salvar o objeto");
		
		return new CarroDTO(rep.save(carro));
	}

	public Carro update(Carro carro,Long id) {
		Assert.notNull(id, "Não foi possível atualizar o objeto");
		
		Optional<Carro> carroBase = rep.findById(id);
		if (carroBase.isPresent()) {
			Carro car = carroBase.get();
			
			car.setNome(carro.getNome()); 
			car.setTipo(carro.getTipo());
			
			rep.save(car);
			
			return car;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
		
	}

	public void delete(Long id) {
		Optional<Carro> carro = rep.findById(id);
		
		rep.delete(carro.get());
	}
	
}
