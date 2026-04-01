package com.pruebassr.ssr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebassr.ssr.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
