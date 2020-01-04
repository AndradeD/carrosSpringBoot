package com.carros.api;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(carroService.getCarros());
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/{id}")
	public ResponseEntity getById(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = carroService.getById(id);
		
		if (carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/tipo/{tipo}")
	public ResponseEntity getByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = carroService.getCarrosByTipo(tipo);
		
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros);
		}
	}
	
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<CarroDTO> post(@RequestBody Carro carro) {
		try {
			CarroDTO c = carroService.save(carro);
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
		
		} catch(Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping(path = "/{id}")
	public String update(@PathVariable Long id, @RequestBody Carro carro) {
		Carro c = carroService.update(carro,id);
		
		return "Carro atualizado com sucesso : "+ c.getId();
	}
	
	@DeleteMapping(path = "/{id}")
	public String delete(@PathVariable Long id) {
		carroService.delete(id);
		
		return "Carro deletado com sucesso.";
	}
}
