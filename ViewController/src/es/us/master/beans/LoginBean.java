package es.us.master.beans;

import es.us.master.entities.Usuario;

import javax.annotation.Generated;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LoginBean {
    private FacesContext context;
    
    @EJB
    private UsuarioBeanLocal usuarioBean;
    private Usuario usuario;
    private HtmlForm form1;

    public LoginBean() {
        usuario = new Usuario();
        context = FacesContext.getCurrentInstance();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public String login() {
        String res = "OK";
        
        Usuario u = usuarioBean.getUsuarioUsernamePassword( usuario.getUsername(), usuario.getPassword() );

        if ( u == null ) {
            context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Error", "No existe un usuario con esa contraseña" ) );

            res = "ERROR";
        }
        else {
            context.getExternalContext().getSessionMap().put( "usuario", u );
        }
        
        return res;
    }
}
