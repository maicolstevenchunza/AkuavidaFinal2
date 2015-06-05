package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.Producto;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.ProductoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("productoController")
@SessionScoped
public class ProductoController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.ProductoFacade ejbFacade;
    private List<Producto> items = null;
    private List<Producto> itemsBuscados = null;
    private Producto selected;
    private Producto selectedBuscar;
    private String idBuscar;
    private String nombreBuscar;
    private Integer IDCategoriaBuscar;
    private boolean ActivoProducto;
    
    

    public ProductoController() {
    }

    public Producto getSelected() {
        return selected;
    }

    public void setSelected(Producto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProductoFacade getFacade() {
        return ejbFacade;
    }

    public Producto prepareCreate() {
        selected = new Producto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProductoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProductoUpdated"));
        selectedBuscar = null;
        itemsBuscados = null;
    }

    public void updateBuscar() {
        persist(PersistAction.UPDATEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("ProductoUpdated"));
        items = null;
        selected = null;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProductoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }

    }

    public void eliminarBuscado() {
        persist(PersistAction.DELETEBUSCAR, ResourceBundle.getBundle("/Bundle").getString("ProductoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        itemsBuscados = null;
        selectedBuscar = null;

    }

    public List<Producto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Producto> buscarPorId() {
        itemsBuscados = getFacade().finById(idBuscar);
        nombreBuscar = null;
        IDCategoriaBuscar = null;
        items = null;
        return itemsBuscados;
    }

    public List<Producto> buscarPorNombre() {
        itemsBuscados = getFacade().findByNombre(nombreBuscar);
        items = null;
        idBuscar = null;
        IDCategoriaBuscar = null;
        return itemsBuscados;
    }

    public List<Producto> buscarPorIdCategoria() {
        itemsBuscados = getFacade().findByIdCategoria(IDCategoriaBuscar);
        items = null;
        idBuscar = null;
        nombreBuscar = null;
        return itemsBuscados;
    }
    
        public List<Producto> buscarPorActivo() {
        itemsBuscados = getFacade().findByActiva(ActivoProducto);
        items = null;
        idBuscar = null;
        nombreBuscar = null;
        IDCategoriaBuscar = null;
        return itemsBuscados;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }

        if (selectedBuscar != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETEBUSCAR) {
                    getFacade().edit(selectedBuscar);
                } else {
                    getFacade().remove(selectedBuscar);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Producto getProducto(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Producto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Producto> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    public String getIdBuscar() {
        return idBuscar;
    }

    public void setIdBuscar(String idBuscar) {
        this.idBuscar = idBuscar;
    }

    public List<Producto> getItemsBuscados() {
        return itemsBuscados;
    }

    public void setItemsBuscados(List<Producto> itemsBuscados) {
        this.itemsBuscados = itemsBuscados;
    }

    public Producto getSelectedBuscar() {
        return selectedBuscar;
    }

    public void setSelectedBuscar(Producto selectedBuscar) {
        this.selectedBuscar = selectedBuscar;
    }

    public String getNombreBuscar() {
        return nombreBuscar;
    }

    public void setNombreBuscar(String nombreBuscar) {
        this.nombreBuscar = nombreBuscar;
    }

    public void setItems(List<Producto> items) {
        this.items = items;
    }

    public Integer getIDCategoriaBuscar() {
        return IDCategoriaBuscar;
    }

    public void setIDCategoriaBuscar(Integer IDCategoriaBuscar) {
        this.IDCategoriaBuscar = IDCategoriaBuscar;
    }

    public boolean isActivoProducto() {
        return ActivoProducto;
    }

    public void setActivoProducto(boolean ActivoProducto) {
        this.ActivoProducto = ActivoProducto;
    }

   

    @FacesConverter(forClass = Producto.class)
    public static class ProductoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoController controller = (ProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productoController");
            return controller.getProducto(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Producto) {
                Producto o = (Producto) object;
                return getStringKey(o.getIDproducto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Producto.class.getName()});
                return null;
            }
        }

    }

}
