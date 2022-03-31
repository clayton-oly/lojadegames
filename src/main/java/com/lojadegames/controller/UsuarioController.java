package com.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojadegames.model.Usuario;
import com.lojadegames.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository usarioRepository;
	
	@GetMapping 
	public ResponseEntity <List<Usuario>> getAll(){
		return ResponseEntity.ok(usarioRepository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Usuario> getById(@PathVariable Long id){
		return usarioRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
											.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity <Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
		if (usarioRepository.existsById(usuario.getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(usarioRepository.save(usuario));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity <Usuario> putUsuario(@RequestBody Usuario usuario){
		if (usarioRepository.existsById(usuario.getId()))
			return ResponseEntity.status(HttpStatus.OK).body(usarioRepository.save(usuario));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
		return usarioRepository.findById(id).map(resposta -> {
			usarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
		

}
