package es.us.master.beans;

import es.us.master.entities.Usuario;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName = "ProyectoWeb-Model-UsuarioBean")
public class UsuarioBean implements UsuarioBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "ProyectoWeb")
    private EntityManager em;

    public UsuarioBean() {
    }

    public Usuario persistUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario;
    }

    public Usuario mergeUsuario(Usuario usuario) {
        return em.merge(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuario = em.find(Usuario.class, usuario.getId());
        em.remove(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> getUsuarioFindAll() {
        return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
    }

    @Override
    public Usuario getUsuarioUsernamePassword(String username, String password) {
        Usuario res = null;
        Query query = em.createNamedQuery( "Usuario.findByUsernamePassword", Usuario.class );
        query.setParameter( "u", username );
        query.setParameter( "p", password );
        
        res = (Usuario) query.getSingleResult();
        
        return res;
    }
}
