package com.example.fut_order.service;
import com.example.fut_order.dto.usuario.UsuarioCreateDTO;
import com.example.fut_order.dto.usuario.UsuarioResponseDTO;
import com.example.fut_order.dto.usuario.UsuarioUpdateDTO;
import com.example.fut_order.exceptions.UsuarioJaCadastradoException;
import com.example.fut_order.exceptions.UsuarioNaoEncontradoException;
import com.example.fut_order.model.Usuario;
import com.example.fut_order.model.enums.TipoPapel;
import com.example.fut_order.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioCreateDTO usuarioCreateDTO){
        if (usuarioRepository.existsByEmail(usuarioCreateDTO.email())){
            throw new UsuarioJaCadastradoException(usuarioCreateDTO.email());
        }
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCreateDTO,usuario );
        usuario.setPapel(TipoPapel.CLIENTE);
        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioResponseDTO(usuario))
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id).map(UsuarioResponseDTO::new)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException(id));

        if (usuarioUpdateDTO.nome() != null){
            usuario.setNome(usuarioUpdateDTO.nome());
        }
        if (usuarioUpdateDTO.email() != null) {
            boolean emailMudou = !usuarioUpdateDTO.email().equals(usuario.getEmail());

            if (emailMudou && usuarioRepository.existsByEmail(usuarioUpdateDTO.email())) {
                throw new UsuarioJaCadastradoException(usuarioUpdateDTO.email());
            }

            usuario.setEmail(usuarioUpdateDTO.email());
        }

        if (usuarioUpdateDTO.senha() != null){
            usuario.setSenha(usuarioUpdateDTO.senha());
        }

        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }


    public void deletarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        usuarioRepository.delete(usuario);
    }
}
