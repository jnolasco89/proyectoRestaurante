/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Nolasco
 */
@Entity
@Table(name = "subtipoproducto")
@NamedQueries({
    @NamedQuery(name = "Subtipoproducto.findAll", query = "SELECT s FROM Subtipoproducto s"),
    @NamedQuery(name = "Subtipoproducto.findById", query = "SELECT s FROM Subtipoproducto s WHERE s.id = :id"),
    @NamedQuery(name = "Subtipoproducto.findByNombre", query = "SELECT s FROM Subtipoproducto s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Subtipoproducto.findByDescripcion", query = "SELECT s FROM Subtipoproducto s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Subtipoproducto.findByEstado", query = "SELECT s FROM Subtipoproducto s WHERE s.estado = :estado")})
public class Subtipoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "idTipoProducto", referencedColumnName = "id")
    @ManyToOne
    private Tipodeproducto idTipoProducto;

    public Subtipoproducto() {
    }

    public Subtipoproducto(Integer id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Tipodeproducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Tipodeproducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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
        if (!(object instanceof Subtipoproducto)) {
            return false;
        }
        Subtipoproducto other = (Subtipoproducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Subtipoproducto[ id=" + id + " ]";
    }
    
}
