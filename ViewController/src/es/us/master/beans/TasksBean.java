package es.us.master.beans;

import es.us.master.entities.Tarea;

import es.us.master.entities.Usuario;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class TasksBean {
    private FacesContext context;
    
    @EJB
    private TareaBeanLocal tareaBean;
    private Usuario usuario;

    public TasksBean() {
        context = FacesContext.getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get( "usuario" );
    }
    
    public List<Tarea> getTasks() {
        return tareaBean.getTareaFindOwn( usuario );
    }
    
    public String logout() {
        context.getExternalContext().invalidateSession();
        context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "OK", "Ha sido desconectado." ) );
        
        return "EXIT";
    }
}
