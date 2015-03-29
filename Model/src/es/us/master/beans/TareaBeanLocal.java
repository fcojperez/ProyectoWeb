package es.us.master.beans;

import es.us.master.entities.Tarea;

import es.us.master.entities.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface TareaBeanLocal {
    Tarea persistTarea(Tarea tarea);

    Tarea mergeTarea(Tarea tarea);

    void removeTarea(Tarea tarea);

    List<Tarea> getTareaFindAll();
    
    List<Tarea> getTareaFindOwn(Usuario usuario);
}
