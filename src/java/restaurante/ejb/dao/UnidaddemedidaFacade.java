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
import restaurante.entidades.Unidaddemedida;
import restaurante.negocio.busquedaPorEstado;

/**
 *
 * @author Nolasco
 */
@Stateless
public class UnidaddemedidaFacade extends AbstractFacade<Unidaddemedida> {

    @PersistenceContext(unitName = "restaurante1.2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidaddemedidaFacade() {
        super(Unidaddemedida.class);
    }

    public List<Unidaddemedida> findByEstado(int estado) {
        List<Unidaddemedida> um;
        
        if (estado == busquedaPorEstado.ACTIVO.getValue()) {
            um = em.createNamedQuery("Unidaddemedida.findByEstado").setParameter("estado", true).getResultList();
        } else if (estado == busquedaPorEstado.INACTIVO.getValue()) {
            um = em.createNamedQuery("Unidaddemedida.findByEstado").setParameter("estado", false).getResultList();
        } else {
            um = em.createNamedQuery("Unidaddemedida.findAll").getResultList();
        }

        return um;
    }
}
