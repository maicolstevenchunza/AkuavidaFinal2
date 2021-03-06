package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.Inventario;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.InventarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("inventarioController")
@ViewScoped
public class InventarioController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.InventarioFacade ejbFacade;
    private List<Inventario> items = null;
    private Inventario selected;

    public InventarioController() {
    }

    public Inventario getSelected() {
        return selected;
    }

    public void setSelected(Inventario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getInventarioPK().setProductoIDproducto(selected.getProducto().getIDproducto());
    }

    protected void initializeEmbeddableKey() {
        selected.setInventarioPK(new edu.co.sena.akuavidaversionfinal.model.entities.InventarioPK());
    }

    private InventarioFacade getFacade() {
        return ejbFacade;
    }

    public Inventario prepareCreate() {
        selected = new Inventario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InventarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InventarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InventarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Inventario> getItems() {
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

    public Inventario getInventario(edu.co.sena.akuavidaversionfinal.model.entities.InventarioPK id) {
        return getFacade().find(id);
    }

    public List<Inventario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Inventario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Inventario.class)
    public static class InventarioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InventarioController controller = (InventarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventarioController");
            return controller.getInventario(getKey(value));
        }

        edu.co.sena.akuavidaversionfinal.model.entities.InventarioPK getKey(String value) {
            edu.co.sena.akuavidaversionfinal.model.entities.InventarioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new edu.co.sena.akuavidaversionfinal.model.entities.InventarioPK();
            key.setIDInventario(Integer.parseInt(values[0]));
            key.setProductoIDproducto(values[1]);
            return key;
        }

        String getStringKey(edu.co.sena.akuavidaversionfinal.model.entities.InventarioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIDInventario());
            sb.append(SEPARATOR);
            sb.append(value.getProductoIDproducto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Inventario) {
                Inventario o = (Inventario) object;
                return getStringKey(o.getInventarioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Inventario.class.getName()});
                return null;
            }
        }

    }

}
