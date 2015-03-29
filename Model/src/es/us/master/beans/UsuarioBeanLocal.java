package es.us.master.beans;

import es.us.master.entities.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UsuarioBeanLocal {
    Usuario persistUsuario(Usuario usuario);

    Usuario mergeUsuario(Usuario usuario);

    void removeUsuario(Usuario usuario);

    List<Usuario> getUsuarioFindAll();
    
    Usuario getUsuarioUsernamePassword(String username, String password);
}
