/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Nolasco
 */
@Entity
@Table(name = "unidaddemedida")
@NamedQueries({
    @NamedQuery(name = "Unidaddemedida.findAll", query = "SELECT u FROM Unidaddemedida u"),
    @NamedQuery(name = "Unidaddemedida.findById", query = "SELECT u FROM Unidaddemedida u WHERE u.id = :id"),
    @NamedQuery(name = "Unidaddemedida.findByNombre", query = "SELECT u FROM Unidaddemedida u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Unidaddemedida.findByAbreviacion", query = "SELECT u FROM Unidaddemedida u WHERE u.abreviacion = :abreviacion"),
    @NamedQuery(name = "Unidaddemedida.findByEstado", query = "SELECT u FROM Unidaddemedida u WHERE u.estado = :estado")})
public class Unidaddemedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 5)
    @Column(name = "abreviacion")
    private String abreviacion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "idUnidaDeMedida")
    private Collection<Medidaproducto> medidaproductoCollection;

    public Unidaddemedida() {
    }

    public Unidaddemedida(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<Medidaproducto> getMedidaproductoCollection() {
        return medidaproductoCollection;
    }

    public void setMedidaproductoCollection(Collection<Medidaproducto> medidaproductoCollection) {
        this.medidaproductoCollection = medidaproductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidaddemedida)) {
            return false;
        }
        Unidaddemedida other = (Unidaddemedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Unidaddemedida[ id=" + id + " ]";
    }
    
}
