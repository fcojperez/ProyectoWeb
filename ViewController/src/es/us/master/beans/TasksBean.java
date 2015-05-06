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
    private boolean edit;
    private Tarea tarea = new Tarea();
    private List<Tarea> tasks;

    public TasksBean() {
        context = FacesContext.getCurrentInstance();
        usuario = (Usuario) context.getExternalContext().getSessionMap().get( "usuario" );

    }
    
    public List<Tarea> getTasks() {
        tasks = tareaBean.getTareaFindOwn( usuario ); 
        return this.tasks;
    }
    
    public int getTasksSize(){
        return tareaBean.getTareaFindOwn(usuario).size();
        }
    
    public String edit(Tarea tarea){
        //Tomamos la tarera que vamos a editar
        setTarea(tarea);
        //Analizar
        //tareaBean.mergeTarea(tarea);
        //tasks = tareaBean.getTareaFindOwn( usuario ); 
        setEdit(true);
        return null;
        }
    public String save(Tarea tarea){
        //Persistimo la tarea
        tareaBean.mergeTarea(tarea);
        setEdit(false);
        //actualizamos la lista de tareas
        tasks = tareaBean.getTareaFindOwn(usuario);
        return null;
        }
    
    public String delete(Tarea tarea){
        tasks.remove(tarea);
        tareaBean.removeTarea(tarea);
        setEdit(false);
        return null;
        }
    
    
    public String logout() {
        context.getExternalContext().invalidateSession();
        context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_INFO, "OK", "Ha sido desconectado." ) );
        
        return "EXIT";
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
