package restaurante.controller;

import restaurante.entidades.Unidaddemedida;
import restaurante.controller.util.JsfUtil;
import restaurante.controller.util.PaginationHelper;
import restaurante.ejb.dao.UnidaddemedidaFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;

@Named("unidaddemedidaController")
@SessionScoped
public class UnidaddemedidaController implements Serializable {

    private Unidaddemedida current;
    private DataModel items = null;
    private int buscarPorEstado;
    private boolean renderBtnAdd;
    private boolean renderBtnEdit;
    private boolean renderBtnDel;
    private boolean renderBtnReact;
    
    @EJB
    private restaurante.ejb.dao.UnidaddemedidaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UnidaddemedidaController() {
    }

    @PostConstruct
    public void init(){
        buscarPorEstado=1;
    }
    
    public Unidaddemedida getSelected() {
        if (current == null) {
            current = new Unidaddemedida();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UnidaddemedidaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Unidaddemedida) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Unidaddemedida();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
//            getFacade().create(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UnidaddemedidaCreated"));
//            return prepareCreate();
            boolean agregado;
            current.setEstado(true);
            getFacade().create(current);
            current = null;
            items = getPagination().createPageDataModel();
            agregado = true;
            PrimeFaces.current().ajax().addCallbackParam("agregado", agregado);

            mostrarMsj("Unidad de medida registrada.");

            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Unidaddemedida) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
//            getFacade().edit(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UnidaddemedidaUpdated"));
//            return "View";
            boolean editado;
            getFacade().edit(current);
            editado = true;
            PrimeFaces.current().ajax().addCallbackParam("editado", editado);
            mostrarMsj("Unidad de medida \"" + current.getNombre() + "\" editada.");
            current = null;
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Unidaddemedida) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UnidaddemedidaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Unidaddemedida getUnidaddemedida(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Unidaddemedida.class)
    public static class UnidaddemedidaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UnidaddemedidaController controller = (UnidaddemedidaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "unidaddemedidaController");
            return controller.getUnidaddemedida(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Unidaddemedida) {
                Unidaddemedida o = (Unidaddemedida) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Unidaddemedida.class.getName());
            }
        }

    }

    //********************************************************************************************
    //********************************************************************************************
    public String estadoToString(boolean e){
        if(e){
            return "Activo";
        }else{
            return "Inactivo";
        }
    }
    
    public void eliminarElemento(Unidaddemedida p) {
        try {
            p.setEstado(false);
            getFacade().edit(p);
            mostrarMsj("Unidad de medida \"" + p.getNombre() + "\" dada de baja");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
       public void reactivarElemento(Unidaddemedida p) {
        try {
            p.setEstado(true);
            getFacade().edit(p);
            mostrarMsj("Unidad de medida \"" + p.getNombre() + "\" reactivada");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void mostrarMsj(String msj) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa", msj);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void setSelected(Unidaddemedida u){
        current=u;
    }
    
    public DataModel getItemsByEstado(){
        items=new ListDataModel(getFacade().findByEstado(getBuscarPorEstado()));
        
        return items;
    }
    
    public int getBuscarPorEstado(){
        if(buscarPorEstado==0){
            buscarPorEstado=1;
        }
        return buscarPorEstado;
    }
    
    public void setBuscarPorEstado(int e){
        this.buscarPorEstado=e;
    }
    
    public void reiniciarSelected() {
        current = null;
    }
    
    public void setRenderBtnAdd(boolean b){
        renderBtnAdd=b;
    }
    
    public boolean getRenderBtnAdd(){
        renderBtnAdd = buscarPorEstado==1 || buscarPorEstado==3;
        return renderBtnAdd;
    }
    
    public void setRenderBtnEdit(boolean b){
        renderBtnEdit=b;
    }
    
    public boolean getRenderBtnEdit(){
        renderBtnEdit = buscarPorEstado==1 || buscarPorEstado==3;
        return renderBtnEdit;
    }
    
    public void setRenderBtnDel(boolean b){
        renderBtnDel=b;
    }
    
    public boolean getRenderBtnDel(){
        renderBtnDel = buscarPorEstado==1 || buscarPorEstado==3;
        return renderBtnDel;
    }
    
    public void setRenderBtnReact(boolean b){
        renderBtnReact=b;
    }
    
    public boolean getRenderBtnReact(){
        renderBtnReact = buscarPorEstado==2;
        return renderBtnReact;
    }
}
