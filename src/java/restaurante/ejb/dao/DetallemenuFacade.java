/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.ejb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import restaurante.entidades.Detallemenu;

/**
 *
 * @author Nolasco
 */
@Stateless
public class DetallemenuFacade extends AbstractFacade<Detallemenu> {

    @PersistenceContext(unitName = "restaurante1.2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallemenuFacade() {
        super(Detallemenu.class);
    }
    
}
