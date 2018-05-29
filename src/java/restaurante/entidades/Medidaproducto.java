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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nolasco
 */
@Entity
@Table(name = "medidaproducto")
@NamedQueries({
    @NamedQuery(name = "Medidaproducto.findAll", query = "SELECT m FROM Medidaproducto m"),
    @NamedQuery(name = "Medidaproducto.findByIdMedida", query = "SELECT m FROM Medidaproducto m WHERE m.idMedida = :idMedida")})
public class Medidaproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedida")
    private Integer idMedida;
    @OneToMany(mappedBy = "idMedida")
    private Collection<Detallemedidaproducto> detallemedidaproductoCollection;
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    @ManyToOne
    private Producto idProducto;
    @JoinColumn(name = "idUnidaDeMedida", referencedColumnName = "id")
    @ManyToOne
    private Unidaddemedida idUnidaDeMedida;

    public Medidaproducto() {
    }

    public Medidaproducto(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public Collection<Detallemedidaproducto> getDetallemedidaproductoCollection() {
        return detallemedidaproductoCollection;
    }

    public void setDetallemedidaproductoCollection(Collection<Detallemedidaproducto> detallemedidaproductoCollection) {
        this.detallemedidaproductoCollection = detallemedidaproductoCollection;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Unidaddemedida getIdUnidaDeMedida() {
        return idUnidaDeMedida;
    }

    public void setIdUnidaDeMedida(Unidaddemedida idUnidaDeMedida) {
        this.idUnidaDeMedida = idUnidaDeMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedida != null ? idMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidaproducto)) {
            return false;
        }
        Medidaproducto other = (Medidaproducto) object;
        if ((this.idMedida == null && other.idMedida != null) || (this.idMedida != null && !this.idMedida.equals(other.idMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Medidaproducto[ idMedida=" + idMedida + " ]";
    }
    
}
