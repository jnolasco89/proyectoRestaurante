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
@Table(name = "detallemedidaproducto")
@NamedQueries({
    @NamedQuery(name = "Detallemedidaproducto.findAll", query = "SELECT d FROM Detallemedidaproducto d"),
    @NamedQuery(name = "Detallemedidaproducto.findById", query = "SELECT d FROM Detallemedidaproducto d WHERE d.id = :id"),
    @NamedQuery(name = "Detallemedidaproducto.findByCantidad", query = "SELECT d FROM Detallemedidaproducto d WHERE d.cantidad = :cantidad")})
public class Detallemedidaproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @JoinColumn(name = "idMedida", referencedColumnName = "idMedida")
    @ManyToOne
    private Medidaproducto idMedida;

    public Detallemedidaproducto() {
    }

    public Detallemedidaproducto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Medidaproducto getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Medidaproducto idMedida) {
        this.idMedida = idMedida;
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
        if (!(object instanceof Detallemedidaproducto)) {
            return false;
        }
        Detallemedidaproducto other = (Detallemedidaproducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Detallemedidaproducto[ id=" + id + " ]";
    }
    
}
