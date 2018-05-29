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
import javax.validation.constraints.Size;

/**
 *
 * @author Nolasco
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByCodigo", query = "SELECT p FROM Producto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByMarca", query = "SELECT p FROM Producto p WHERE p.marca = :marca"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByEstado", query = "SELECT p FROM Producto p WHERE p.estado = :estado"),

    @NamedQuery(name = "Producto.findAllPlatos", query = "SELECT p FROM Producto p WHERE p.idTipoProducto.id = :idTipoProducto"),
    @NamedQuery(name = "Producto.findAllPlatosByEstado", query = "SELECT p FROM Producto p WHERE p.idTipoProducto.id = :idTipoProducto AND p.estado = :estado"),
    @NamedQuery(name = "Producto.TiposDePlato", query = "SELECT p.idTipoProducto FROM Producto p WHERE p.idTipoProducto.id = :idTipoProducto"),
    @NamedQuery(name = "Producto.UpdateEstado", query = "UPDATE Producto p SET p.estado = p.estado")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "marca")
    private String marca;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Detallemenu> detallemenuCollection;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Medidaproducto> medidaproductoCollection;
    @JoinColumn(name = "idTipoProducto", referencedColumnName = "id")
    @ManyToOne
    private Tipodeproducto idTipoProducto;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Detalleproducto> detalleproductoCollection;
    @OneToMany(mappedBy = "idDetalle")
    private Collection<Detalleproducto> detalleproductoCollection1;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Pedido> pedidoCollection;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<Detallemenu> getDetallemenuCollection() {
        return detallemenuCollection;
    }

    public void setDetallemenuCollection(Collection<Detallemenu> detallemenuCollection) {
        this.detallemenuCollection = detallemenuCollection;
    }

    public Collection<Medidaproducto> getMedidaproductoCollection() {
        return medidaproductoCollection;
    }

    public void setMedidaproductoCollection(Collection<Medidaproducto> medidaproductoCollection) {
        this.medidaproductoCollection = medidaproductoCollection;
    }

    public Tipodeproducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Tipodeproducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Collection<Detalleproducto> getDetalleproductoCollection() {
        return detalleproductoCollection;
    }

    public void setDetalleproductoCollection(Collection<Detalleproducto> detalleproductoCollection) {
        this.detalleproductoCollection = detalleproductoCollection;
    }

    public Collection<Detalleproducto> getDetalleproductoCollection1() {
        return detalleproductoCollection1;
    }

    public void setDetalleproductoCollection1(Collection<Detalleproducto> detalleproductoCollection1) {
        this.detalleproductoCollection1 = detalleproductoCollection1;
    }

    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.entidades.Producto[ id=" + id + " ]";
    }
    
}
