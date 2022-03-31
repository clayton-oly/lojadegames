package com.lojadegames.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojadegames.model.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository <Usuario, Long>{
	
	//public List <Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
}
