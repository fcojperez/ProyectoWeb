package es.us.master.entities;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "select o from Tarea o"),
    @NamedQuery(name = "Tarea.findOwn", query = "select o from Tarea o where o.usuario =:u")
})
@SequenceGenerator(name = "Tarea_Id_Seq_Gen", sequenceName = "TAREA_ID_SEQ_GEN", allocationSize = 50, initialValue = 50)
@SuppressWarnings("oracle.toplink.jpa.query.jpql-query-invalid")
public class Tarea implements Serializable {
    private static final long serialVersionUID = 6642433660284463910L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Tarea_Id_Seq_Gen")
    private Integer id;
    
    @Version
    private Integer version;
    
    private String descripcion;
    
    @Column(nullable = false)
    private String estado = "activo";
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInicio;
    
    @Column(nullable = false)
    private String titulo;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    @ManyToOne
    private Usuario usuario;

    public Tarea() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Tarea)) {
            return false;
        }
        final Tarea other = (Tarea) object;
        if (!(id == null ? other.id == null : id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
