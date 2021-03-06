package edu.co.sena.akuavidaversionfinal.view.administrador.bean;

import edu.co.sena.akuavidaversionfinal.model.entities.Domicilio;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil;
import edu.co.sena.akuavidaversionfinal.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.DomicilioFacade;

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
import javax.faces.view.ViewScoped;

@Named("domicilioController")
@ViewScoped
public class DomicilioController implements Serializable {

    @EJB
    private edu.co.sena.akuavidaversionfinal.controlller.administrador.beans.DomicilioFacade ejbFacade;
    private List<Domicilio> items = null;
    private Domicilio selected;

    public DomicilioController() {
    }

    public Domicilio getSelected() {
        return selected;
    }

    public void setSelected(Domicilio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getDomicilioPK().setCuentaTipoDocumento(selected.getCuenta().getCuentaPK().getTipoDocumento());
        selected.getDomicilioPK().setCuentaNumeroDocumento(selected.getCuenta().getCuentaPK().getNumeroDocumento());
    }

    protected void initializeEmbeddableKey() {
        selected.setDomicilioPK(new edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK());
    }

    private DomicilioFacade getFacade() {
        return ejbFacade;
    }

    public Domicilio prepareCreate() {
        selected = new Domicilio();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DomicilioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DomicilioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DomicilioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Domicilio> getItems() {
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

    public Domicilio getDomicilio(edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK id) {
        return getFacade().find(id);
    }

    public List<Domicilio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Domicilio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Domicilio.class)
    public static class DomicilioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DomicilioController controller = (DomicilioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "domicilioController");
            return controller.getDomicilio(getKey(value));
        }

        edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK getKey(String value) {
            edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK();
            key.setCuentaTipoDocumento(values[0]);
            key.setCuentaNumeroDocumento(values[1]);
            return key;
        }

        String getStringKey(edu.co.sena.akuavidaversionfinal.model.entities.DomicilioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCuentaTipoDocumento());
            sb.append(SEPARATOR);
            sb.append(value.getCuentaNumeroDocumento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Domicilio) {
                Domicilio o = (Domicilio) object;
                return getStringKey(o.getDomicilioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Domicilio.class.getName()});
                return null;
            }
        }

    }

}
