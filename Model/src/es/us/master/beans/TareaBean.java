package es.us.master.beans;

import es.us.master.entities.Tarea;

import es.us.master.entities.Usuario;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName = "ProyectoWeb-Model-TareaBean")
public class TareaBean implements TareaBeanLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "ProyectoWeb")
    private EntityManager em;

    public TareaBean() {
    }

    public Tarea persistTarea(Tarea tarea) {
        em.persist(tarea);
        return tarea;
    }

    public Tarea mergeTarea(Tarea tarea) {
        return em.merge(tarea);
    }

    public void removeTarea(Tarea tarea) {
        tarea = em.find(Tarea.class, tarea.getId());
        em.remove(tarea);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Tarea> getTareaFindAll() {
        return em.createNamedQuery("Tarea.findAll", Tarea.class).getResultList();
    }

    @Override
    public List<Tarea> getTareaFindOwn(Usuario usuario) {
        Query query = em.createNamedQuery( "Tarea.findOwn", Tarea.class );
        query.setParameter( "u", usuario );
        
        return query.getResultList();

    }
}
