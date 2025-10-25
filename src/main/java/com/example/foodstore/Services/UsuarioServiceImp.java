package com.example.foodstore.Services;

import com.example.foodstore.Repository.UsuarioRepository;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.UsuarioCreate;
import com.example.foodstore.entity.dtos.UsuarioDto;
import com.example.foodstore.entity.dtos.UsuarioEdit;
import com.example.foodstore.entity.dtos.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;//llama a la interfaz usuarorepository

    @Override
    public UsuarioDto crear(UsuarioCreate u) {
        Usuario usuario = UsuarioMapper.toEntity(u);

        usuario = usuarioRepository.save(usuario);

        UsuarioDto usuarioDto = UsuarioMapper.toDTo(usuario);
        return usuarioDto;

    }

    @Override

    public UsuarioDto actualizar(Long id, UsuarioEdit u) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
          //  usuario.setNombre(u.getNombre());
            //usuario.setApellido(u.getApellido());
            usuario.setMail(u.getMail());
            usuario.setCelular(u.getCelular());
            usuario.setContrasenia(u.getContrasenia());

            usuario = usuarioRepository.save(usuario);

            return UsuarioMapper.toDTo(usuario);

        }
        return null;


    }

    @Override

    public UsuarioDto buscaId(Long id) {
        Optional<Usuario>usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            if (!usuarioOptional.get().isEliminado()) {
                return UsuarioMapper.toDTo(usuarioOptional.get());

            }

        }return null;


    }
    
    @Override
    public List<UsuarioDto> buscaTodos(){
        List<Usuario>usuarios=usuarioRepository.findAllByEliminadoFalse();
        List<UsuarioDto>usuarioDtos=usuarios.stream().map(UsuarioMapper::toDTo).toList();
        return usuarioDtos;
    }

    @Override
    public void eliminar (Long id){
        Optional<Usuario> usuario= usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario u =usuario.get();
            u.setEliminado(true);
            usuarioRepository.save(u);

        }


    }




}



