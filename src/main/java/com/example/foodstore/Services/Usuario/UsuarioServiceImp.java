package com.example.foodstore.Services.Usuario;

import com.example.foodstore.Repository.UsuarioRepository;
import com.example.foodstore.Utils.HashUtil;
import com.example.foodstore.entity.Rol;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dtos.Usuario.UsuarioCreate;
import com.example.foodstore.entity.dtos.Usuario.UsuarioDto;
import com.example.foodstore.entity.dtos.Usuario.UsuarioEdit;
import com.example.foodstore.entity.dtos.Usuario.UsuarioMapper;
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
        if (usuarioRepository.existsByMail(u.getMail())) {
            return null;
        }
        Usuario usuario = UsuarioMapper.toEntity(u);
        //Ahi se utiliza el metodo hash
        String hash = HashUtil.sha256(usuario.getContrasenia());
        usuario.setContrasenia(hash);
        usuario.setRol(Rol.USUARIO);
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

            if (u.getContrasenia() != null && !u.getContrasenia().isEmpty()) {
                usuario.setContrasenia(HashUtil.sha256(u.getContrasenia()));
            }

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
        List<Usuario>usuarios=usuarioRepository.findAllByIsEliminadoFalse();
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



