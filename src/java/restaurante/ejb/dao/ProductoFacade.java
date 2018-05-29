/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.ejb.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import restaurante.entidades.Producto;
import restaurante.entidades.Detalleproducto;
import restaurante.negocio.Plato;
import restaurante.negocio.Porcion;
import restaurante.negocio.busquedaPorEstado;

/**
 *
 * @author Nolasco
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "restaurante1.2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    public List<Plato> findAllPlatos() {
        List<Producto> productos = em.createNamedQuery("Producto.findAllPlatos").setParameter("idTipoProducto", 8).getResultList();//.setParameter("tipoDeProducto", 8)
        List<Plato> platos=mapeoProdctosPlatos(productos);
        
        return platos;
//        for (int i = 0; i < productos.size(); i++) {
//            Producto plato = productos.get(i);
//
//            //Obteniendo las porciones que contiene un Plato
//            List<Porcion> porciones = new ArrayList<>();
//            Object[] detPro = plato.getDetalleproductoCollection().toArray();
//
//            for (Object elemento : detPro) {
//                Detalleproducto detalle = (Detalleproducto) elemento;
//                Producto porcion = (Producto) em.createNamedQuery("Producto.findById").setParameter("id", detalle.getIdDetalle().getId()).getSingleResult();
//                porciones.add(new Porcion(porcion.getId(), porcion.getCodigo(), porcion.getNombre(), null, porcion.getPrecio(), porcion.getEstado()));
//            }
//
//            //Creando el plato y agragando las porciones que se buscaron en el bloque anterior
//            platos.add(new Plato(plato.getId(), plato.getCodigo(), plato.getNombre(), plato.getIdTipoProducto(), plato.getPrecio(), "descripcion", porciones, plato.getEstado()));
//        }
//
//        return platos;
    }

    public List findByEstado(int estado) {
        List<Producto> productos;

        if (estado == busquedaPorEstado.ACTIVO.getValue()) {
            productos = em.createNamedQuery("Producto.findAllPlatosByEstado").setParameter("idTipoProducto", 8).setParameter("estado", true).getResultList();
        } else if (estado == busquedaPorEstado.INACTIVO.getValue()) {
            productos = em.createNamedQuery("Producto.findAllPlatosByEstado").setParameter("idTipoProducto", 8).setParameter("estado", false).getResultList();
        } else {
            productos = em.createNamedQuery("Producto.findAllPlatos").setParameter("idTipoProducto", 8).getResultList();
        }

        List<Plato> platos=mapeoProdctosPlatos(productos);
        
        return platos;
    }

    private List<Plato> mapeoProdctosPlatos(List<Producto> productos) {
        List<Plato> platos;
        platos = new ArrayList<>();

        for (int i = 0; i < productos.size(); i++) {
            Producto plato = productos.get(i);

            //Obteniendo las porciones que contiene un Plato
            List<Porcion> porciones = new ArrayList<>();
            Object[] detPro = plato.getDetalleproductoCollection().toArray();

            for (Object elemento : detPro) {
                Detalleproducto detalle = (Detalleproducto) elemento;
                Producto porcion = (Producto) em.createNamedQuery("Producto.findById").setParameter("id", detalle.getIdDetalle().getId()).getSingleResult();
                porciones.add(new Porcion(porcion.getId(), porcion.getCodigo(), porcion.getNombre(), null, porcion.getPrecio(), porcion.getEstado()));
            }

            //Creando el plato y agragando las porciones que se buscaron en el bloque anterior
            platos.add(new Plato(plato.getId(), plato.getCodigo(), plato.getNombre(), plato.getIdTipoProducto(), plato.getPrecio(), "descripcion", porciones, plato.getEstado()));
        }

        return platos;
    }
//    public void updateEstadoPlato(Producto p){
//    em.createNamedQuery("Producto.UpdateEstado").setParameter("idTipoProducto", 8).getResultList();//.setParameter("tipoDeProducto", 8)
//    }

}
