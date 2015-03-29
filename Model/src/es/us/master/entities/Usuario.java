package es.us.master.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o"),
    @NamedQuery(name = "Usuario.findByUsernamePassword", query = "select o from Usuario o where o.username= :u and o.password= :p")
})
@SequenceGenerator(name = "Usuario_Id_Seq_Gen", sequenceName = "USUARIO_ID_SEQ_GEN", allocationSize = 50,
                   initialValue = 50)
public class Usuario implements Serializable {
    private static final long serialVersionUID = -1498391986936405062L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario_Id_Seq_Gen")
    private Integer id;
    
    @Version
    private Integer version;
    
    @Column(nullable = false)
    private String apellidos;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private int estado = 1;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String username;
    
    @OneToMany( cascade=CascadeType.ALL)
    @JoinColumn(name="USUARIO_ID")
    private List<Tarea> tareas;

    public Usuario() {
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<Tarea> getTareas() {
        return tareas;
    }
    
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Usuario)) {
            return false;
        }
        final Usuario other = (Usuario) object;
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
