/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.ejb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import restaurante.entidades.Mesa;
import restaurante.negocio.busquedaPorEstado;

/**
 *
 * @author Nolasco
 */
@Stateless
public class MesaFacade extends AbstractFacade<Mesa> {

    @PersistenceContext(unitName = "restaurante1.2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MesaFacade() {
        super(Mesa.class);
    }

    public List findByEstado(int estado) {
        List<Mesa> mesas;
        
        if (estado == busquedaPorEstado.ACTIVO.getValue()) {
            mesas = em.createNamedQuery("Mesa.findByEstado").setParameter("estado", true).getResultList();
        } else if (estado == busquedaPorEstado.INACTIVO.getValue()) {
            mesas = em.createNamedQuery("Mesa.findByEstado").setParameter("estado", false).getResultList();
        } else {
            mesas = em.createNamedQuery("Mesa.findAll").getResultList();
        }

        return mesas;
    }
    
}
