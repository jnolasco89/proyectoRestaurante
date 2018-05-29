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

/**
 *
 * @author Nolasco
 */
@Entity
@Table(name = "detalleproducto")
@NamedQueries({
    @NamedQuery(name = "Detalleproducto.findAll", query = "SELECT d FROM Detalleproducto d"),
    @NamedQuery(name = "Detalleproducto.findById", query = "SELECT d FROM Detalleproducto d WHERE d.id = :id")})
public class Detalleproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    @ManyToOne
    private Producto idProducto;
    @JoinColumn(name = "idDetalle", referencedColumnName = "id")
    @ManyToOne
    private Producto idDetalle;

    public Detalleproducto() {
    }

    public Detalleproducto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Producto getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Producto idDetalle) {
        this.idDetalle = idDetalle;
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
        if (!(object instanceof Detalleproducto)) {
            return false;
        }
        Detalleproducto other = (Detalleproducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Detalleproducto[ id=" + id + " ]";
    }
    
}
