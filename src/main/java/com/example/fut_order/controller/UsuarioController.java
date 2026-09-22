package com.example.fut_order.controller;


import com.example.fut_order.dto.usuario.UsuarioCreateDTO;
import com.example.fut_order.dto.usuario.UsuarioResponseDTO;
import com.example.fut_order.dto.usuario.UsuarioUpdateDTO;
import com.example.fut_order.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity <UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioCreateDTO dto){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioResponseDTO);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateDTO dto){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
