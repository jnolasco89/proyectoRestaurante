/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.FlowEvent;
import restaurante.ejb.dao.ProductoFacade;
import restaurante.entidades.Producto;
import restaurante.negocio.Plato;

/**
 *
 * @author Nolasco
 */
@Named(value = "platoController")
@SessionScoped
public class PlatoController implements Serializable {

    private Plato current;
    private DataModel items = null;

    @EJB
    private ProductoFacade productoFacade;
    private int buscarPorEstado;
    private boolean renderBtnEdit;
    private boolean renderBtnDel;
    private boolean renderBtnReact;

    public PlatoController() {
    }

    //========================================================
    //SETTERS Y GETTERS
    //========================================================
    public Plato getSelected() {
        if (current == null) {
            current = new Plato();
        }
        return current;
    }

    public void setSelected(Plato m) {
        current = m;
    }

    public int getBuscarPorEstado() {
        if (buscarPorEstado == 0) {
            buscarPorEstado = 1;
        }
        return buscarPorEstado;
    }

    public void setBuscarPorEstado(int e) {
        this.buscarPorEstado = e;
    }

    public void setRenderBtnEdit(boolean b) {
        renderBtnEdit = b;
    }

    public boolean getRenderBtnEdit() {
        renderBtnEdit = buscarPorEstado == 1 || buscarPorEstado == 3;
        return renderBtnEdit;
    }

    public void setRenderBtnDel(boolean b) {
        renderBtnDel = b;
    }

    public boolean getRenderBtnDel() {
        renderBtnDel = buscarPorEstado == 1 || buscarPorEstado == 3;
        return renderBtnDel;
    }

    public void setRenderBtnReact(boolean b) {
        renderBtnReact = b;
    }

    public boolean getRenderBtnReact() {
        renderBtnReact = buscarPorEstado == 2;
        return renderBtnReact;
    }

    //========================================================
    //METODOS Y FUNCIONES
    //========================================================
    @PostConstruct
    public void init() {
        buscarPorEstado = 1;
    }

    public DataModel getItemsByEstado() {
        items = new ListDataModel(productoFacade.findByEstado(getBuscarPorEstado()));

        return items;
    }

    public void updateItems() {
        getItemsByEstado();
    }

    public void mostrarMsj(String msj) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", msj);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String estadoToString(boolean e) {
        if (e) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    }

    public String onFlowProcess(FlowEvent event) {
//        if (skip) {
//            skip = false;   //reset in case user goes back
//            return "confirm";
//        } else {
            return event.getNewStep();
        }

        //========================================================
        //METODOS PARA DAR DE BAJA UN ELEMENTO
        //========================================================
//    public void eliminarElemento(Plato m) {
//        try {
//            m.setEstado(false);
//            getFacade().edit(m);
//            mostrarMsj("Mesa \"" + m.getNumero() + "\" dada de baja");
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//        }
//    }
//
//    public void reactivarElemento(Mesa m) {
//        try {
//            m.setEstado(true);
//            getFacade().edit(m);
//            mostrarMsj("Mesa \"" + m.getNumero() + "\" reactivada");
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//        }
//    }
    }
