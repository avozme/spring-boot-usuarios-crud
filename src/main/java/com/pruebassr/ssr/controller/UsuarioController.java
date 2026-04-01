package com.pruebassr.ssr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pruebassr.ssr.model.Usuario;
import com.pruebassr.ssr.service.UsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // LISTAR USUARIOS
    @GetMapping("")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", service.obtenerTodos());
        return "usuarioList";
    }

    // MOSTRAR UN USUARIO
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Usuario usuario = service.obtenerPorId(id);

        if (usuario == null) {
            return "redirect:/usuarios";
        }

        model.addAttribute("usuario", usuario);
        return "usuarioDetalle";
    }

    // FORMULARIO CREAR
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioForm";
    }

    // GUARDAR (CREATE y UPDATE)
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        System.out.println("Guardando usuario id = " + usuario.getId());
        service.guardar(usuario);
        return "redirect:/usuarios";
    }

    // FORMULARIO EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = service.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarioForm";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/usuarios";
    }
}
