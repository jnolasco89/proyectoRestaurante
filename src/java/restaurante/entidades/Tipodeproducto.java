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
@Table(name = "tipodeproducto")
@NamedQueries({
    @NamedQuery(name = "Tipodeproducto.findAll", query = "SELECT t FROM Tipodeproducto t"),
    @NamedQuery(name = "Tipodeproducto.findById", query = "SELECT t FROM Tipodeproducto t WHERE t.id = :id"),
    @NamedQuery(name = "Tipodeproducto.findByNombre", query = "SELECT t FROM Tipodeproducto t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipodeproducto.findByDescripcion", query = "SELECT t FROM Tipodeproducto t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipodeproducto.findByEstado", query = "SELECT t FROM Tipodeproducto t WHERE t.estado = :estado")})
public class Tipodeproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "idTipoProducto")
    private Collection<Subtipoproducto> subtipoproductoCollection;
    @OneToMany(mappedBy = "idTipoProducto")
    private Collection<Producto> productoCollection;

    public Tipodeproducto() {
    }

    public Tipodeproducto(Integer id) {
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

    public Collection<Subtipoproducto> getSubtipoproductoCollection() {
        return subtipoproductoCollection;
    }

    public void setSubtipoproductoCollection(Collection<Subtipoproducto> subtipoproductoCollection) {
        this.subtipoproductoCollection = subtipoproductoCollection;
    }

    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
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
        if (!(object instanceof Tipodeproducto)) {
            return false;
        }
        Tipodeproducto other = (Tipodeproducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Tipodeproducto[ id=" + id + " ]";
    }
    
}
