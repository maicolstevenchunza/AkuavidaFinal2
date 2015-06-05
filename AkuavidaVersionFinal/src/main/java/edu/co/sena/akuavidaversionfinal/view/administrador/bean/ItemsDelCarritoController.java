package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarrito;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.ItemsDelCarritoFacade;

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

@Named("itemsDelCarritoController")
@SessionScoped
public class ItemsDelCarritoController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.ItemsDelCarritoFacade ejbFacade;
    private List<ItemsDelCarrito> items = null;
    private ItemsDelCarrito selected;

    public ItemsDelCarritoController() {
    }

    public ItemsDelCarrito getSelected() {
        return selected;
    }

    public void setSelected(ItemsDelCarrito selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getItemsDelCarritoPK().setProductoIDproducto(selected.getProducto().getIDproducto());
        selected.getItemsDelCarritoPK().setCarritoDeComprasIDCarrito(selected.getCarritoDeCompras().getIDCarrito());
    }

    protected void initializeEmbeddableKey() {
        selected.setItemsDelCarritoPK(new edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK());
    }

    private ItemsDelCarritoFacade getFacade() {
        return ejbFacade;
    }

    public ItemsDelCarrito prepareCreate() {
        selected = new ItemsDelCarrito();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ItemsDelCarritoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ItemsDelCarritoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ItemsDelCarritoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ItemsDelCarrito> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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
    }

    public ItemsDelCarrito getItemsDelCarrito(edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK id) {
        return getFacade().find(id);
    }

    public List<ItemsDelCarrito> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ItemsDelCarrito> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ItemsDelCarrito.class)
    public static class ItemsDelCarritoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemsDelCarritoController controller = (ItemsDelCarritoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemsDelCarritoController");
            return controller.getItemsDelCarrito(getKey(value));
        }

        edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK getKey(String value) {
            edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK();
            key.setProductoIDproducto(values[0]);
            key.setCarritoDeComprasIDCarrito(values[1]);
            return key;
        }

        String getStringKey(edu.co.sena.akuavidaversionfinal.model.entities.ItemsDelCarritoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getProductoIDproducto());
            sb.append(SEPARATOR);
            sb.append(value.getCarritoDeComprasIDCarrito());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ItemsDelCarrito) {
                ItemsDelCarrito o = (ItemsDelCarrito) object;
                return getStringKey(o.getItemsDelCarritoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ItemsDelCarrito.class.getName()});
                return null;
            }
        }

    }

}
